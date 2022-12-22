package AST;

import exceptions.*;
import types.*;
import util.*;
import values.*;
import Environment.*;
public class ASTCompare implements ASTNode {

    private ASTNode lhs;
    private ASTNode rhs;

    private String op;

    public ASTCompare(ASTNode lhs, ASTNode rhs, String operator) {
        this.lhs = lhs;
        this.rhs = rhs;
        op = operator;
    }

    @Override
    public IValue eval(Environment<IValue> env) {
        IValue v1 = lhs.eval(env);
        IValue v2 = rhs.eval(env);

        if (v1 instanceof VInt && v2 instanceof VInt) {
            switch(op) {
                case "==":
                    return new VBool(((VInt) v1).getVal() == ((VInt) v2).getVal());
                case "<":
                    return new VBool(((VInt) v1).getVal() < ((VInt) v2).getVal());
                case ">":
                    return new VBool(((VInt) v1).getVal() > ((VInt) v2).getVal());
                case "<=":
                    return new VBool(((VInt) v1).getVal() <= ((VInt) v2).getVal());
                case ">=":
                    return new VBool(((VInt) v1).getVal() >= ((VInt) v2).getVal());
                default: break;
            }
        }

        // TODO - all types
        return new VBool(((VBool) v1).getVal() == ((VBool) v2).getVal());

    }

    @Override
    public void compile(CodeBlock c, Environment<IValue> env) {
        Labels labels = Labels.getInstance();
        int L1 = labels.getNewLabel();
        int L2 = labels.getNewLabel();
        lhs.compile(c, env);
        rhs.compile(c, env);
        c.emit("isub");

        switch (op) {
            case "==" -> c.emit("ifeq L"+ L1);
            case "<" -> c.emit("iflt L"+ L1);
            case ">" -> c.emit("ifgt L"+ L1);
            case "<=" -> c.emit("ifle L"+ L1);
            case ">=" -> c.emit("ifge L"+ L1);
            default -> throw new IllegalStateException("Unexpected value: " + op);
        }
        c.emit("iconst_0");
        c.emit("goto L" + L2);
        c.emit("L" + L1 + ":");
        c.emit("iconst_1");
        c.emit("L" + L2 + ":");

    }

    @Override
    public IType typecheck(Environment<IType> env) throws TypeError {
        IType tLeft = lhs.typecheck(env);
        IType tRight = rhs.typecheck(env);

        if ( ( tLeft instanceof TypeInt && tRight instanceof TypeInt ) || ( tLeft instanceof TypeBool && tRight instanceof TypeBool && op.equals("==") ) )
            return new TypeBool();

        throw new TypeError(op);
    }
}
