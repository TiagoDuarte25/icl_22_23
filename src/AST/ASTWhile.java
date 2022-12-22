package AST;

import exceptions.*;
import types.*;
import util.*;
import values.*;
import Environment.*;
public class ASTWhile implements ASTNode {

    private static final String OPERATOR = "while";
    private ASTNode condition, body;

    public ASTWhile(ASTNode condition, ASTNode body) {
        this.condition = condition;
        this.body = body;
    }

    @Override
    public IValue eval(Environment<IValue> env) {
        IValue condVal = condition.eval(env);

        IValue bodyVal = null;
        while (((VBool) condVal).getVal()) {
            bodyVal = body.eval(env);
            condVal = condition.eval(env);
        }
        return bodyVal;

    }

    @Override
    public void compile(CodeBlock c, Environment<IValue> env) {
        Labels labels = Labels.getInstance();

        int l1 = labels.getNewLabel();
        c.emit(String.format("L%d:", l1));
        condition.compile(c, env);

        int l2 = labels.getNewLabel();
        c.emit("ifeq L" + l2);

        body.compile(c, env);
        c.emit("pop");
        c.emit("goto L" + l1);

        c.emit(String.format("L%d:", l2));
    }

    @Override
    public IType typecheck(Environment<IType> env) throws TypeError {
        IType condType = condition.typecheck(env);

        if (condType instanceof TypeBool)
            return body.typecheck(env);

        throw new TypeError(OPERATOR);
    }
}
