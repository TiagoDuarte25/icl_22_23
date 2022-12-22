package AST;

import exceptions.*;
import types.*;
import util.*;
import values.*;
import Environment.*;
public class ASTIf implements ASTNode {

    private static final String OPERATOR = "if";

    ASTNode condition, exp1, exp2;

    public ASTIf(ASTNode condition, ASTNode exp1, ASTNode exp2) {
        this.condition = condition;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    @Override
    public IValue eval(Environment<IValue> env) {
        IValue cond = condition.eval(env);
        if (((VBool) cond).getVal())
            return exp1.eval(env);
        else
            return exp2.eval(env);
    }

    @Override
    public void compile(CodeBlock c, Environment<IValue> env) {
        Labels labels = Labels.getInstance();

        condition.compile(c, env);

        int l1 = labels.getNewLabel();
        c.emit("ifeq L" + l1);

        exp1.compile(c, env);

        int l2 = labels.getNewLabel();
        c.emit("goto L" + l2);

        c.emit(String.format("L%d:", l1));
        exp2.compile(c, env);

        c.emit(String.format("L%d:", l2));
    }

    @Override
    public IType typecheck(Environment<IType> env) throws TypeError {
        IType conditionType = condition.typecheck(env);

        if (conditionType instanceof TypeBool) {
            IType exp1Type = exp1.typecheck(env);
            if(exp2 != null) {
                IType exp2Type = exp2.typecheck(env);
                if (exp1Type.equalsType(exp2Type))
                    return exp1Type;
            } else
                return exp1Type;
        }

        throw new TypeError(OPERATOR);
    }
}
