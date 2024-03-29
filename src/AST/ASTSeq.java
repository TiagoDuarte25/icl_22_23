package AST;

import exceptions.*;
import types.*;
import util.*;
import values.*;
import Environment.*;
public class ASTSeq implements ASTNode {

    private ASTNode left, right;

    public ASTSeq(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public IValue eval(Environment<IValue> env) {
        left.eval(env);
        return right.eval(env);
    }

    @Override
    public void compile(CodeBlock c, Environment<IValue> env) {
        left.compile(c, env);
        right.compile(c, env);
    }

    @Override
    public IType typecheck(Environment<IType> env) throws TypeError {
        left.typecheck(env);
        return right.typecheck(env);
    }
}
