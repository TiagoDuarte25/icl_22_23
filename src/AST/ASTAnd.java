package AST;

import exceptions.*;
import types.*;
import util.*;
import values.*;
import Environment.*;

public class ASTAnd implements ASTNode {

    private static final String OPERATOR = "&&";

    private ASTNode lhs;
    private ASTNode rhs;

    public ASTAnd(ASTNode lhs, ASTNode rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public IValue eval(Environment<IValue> env) {
        IValue v1 = lhs.eval(env);
        IValue v2 = rhs.eval(env);

        return new VBool(((VBool) v1).getVal() && ((VBool) v2).getVal());
    }

    @Override
    public void compile(CodeBlock c, Environment<IValue> env) {
        lhs.compile(c, env);
        rhs.compile(c, env);
        c.emit("iand");
    }

    @Override
    public IType typecheck(Environment<IType> env) throws TypeError {
        IType tLeft = lhs.typecheck(env);
        IType tRight = rhs.typecheck(env);

        if (tLeft instanceof TypeBool && tRight instanceof TypeBool)
            return tLeft;

        throw new TypeError(OPERATOR);
    }
}
