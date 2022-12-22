package AST;

import exceptions.*;
import types.*;
import util.*;
import values.*;
import Environment.*;
public class ASTPlus implements ASTNode {

    private static final String OPERATOR = "+";

    private ASTNode lhs, rhs;

    public IValue eval(Environment<IValue> e){
        IValue v1 = lhs.eval(e);
        IValue v2 = rhs.eval(e);

        if (v1 instanceof VInt && v2 instanceof VInt)
            return new VInt(((VInt) v1).getVal() + ((VInt) v2).getVal());

        return new VString(((VString) v1).getVal() + ((VString) v2).getVal());
    }

    public ASTPlus(ASTNode l, ASTNode r) {
        lhs = l; rhs = r;
    }

    @Override
    public void compile(CodeBlock c, Environment<IValue> env) {
        lhs.compile(c, env);
        rhs.compile(c, env);
        IValue v1 = lhs.eval(env);
        IValue v2 = rhs.eval(env);
        if (v1 instanceof VInt && v2 instanceof VInt)
            c.emit("iadd");
        if (v1 instanceof VString && v2 instanceof VString)
            c.emit("invokevirtual java/lang/String/concat(Ljava/lang/String;)Ljava/lang/String;");
    }

    @Override
    public IType typecheck(Environment<IType> env) throws TypeError {
        IType tLeft = lhs.typecheck(env);
        IType tRight = rhs.typecheck(env);

        if ( (tLeft instanceof TypeInt && tRight instanceof TypeInt ) ||
                ( tLeft instanceof TypeString && tRight instanceof TypeString ) )
            return tLeft;

        throw new TypeError(OPERATOR);
    }

}

