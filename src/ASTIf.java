import exceptions.TypeError;
import types.IType;
import types.TypeBool;

public class ASTIf implements ASTNode {

    private static final String OPERATOR = "if";

    ASTNode condition, exp1, exp2;

    /*
    public ASTIf(ASTNode condition, ASTNode exp1) {
        this.condition = condition;
        this.exp1 = exp1;
    }*/

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

    }

    @Override
    public IType typecheck(Environment<IType> env) throws TypeError {
        IType conditionType = condition.typecheck(env);

        if (conditionType instanceof TypeBool) {
            IType exp1Type = exp1.typecheck(env);
            IType exp2Type = exp2.typecheck(env);

            return conditionType;
        }

        throw new TypeError(OPERATOR);
    }
}
