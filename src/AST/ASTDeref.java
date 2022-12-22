package AST;

import exceptions.*;
import types.*;
import Environment.*;
import util.*;
import values.*;

public class ASTDeref implements ASTNode {

    private static final String OPERATOR = "!";

    ASTNode exp;

    public ASTDeref(ASTNode exp) {
        this.exp = exp;
    }

    @Override
    public IValue eval(Environment<IValue> env) {
        IValue v1 = exp.eval(env);
        return ((VCell) v1).getVal();
    }

    @Override
    public void compile(CodeBlock c, Environment<IValue> env) {

    }

    @Override
    public IType typecheck(Environment<IType> env) throws TypeError {
        IType expType = exp.typecheck(env);

        if (expType instanceof TypeRef)
            return ((TypeRef) expType).getType();

        throw new TypeError(OPERATOR);
    }

}
