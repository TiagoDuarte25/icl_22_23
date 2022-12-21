import exceptions.TypeError;
import types.IType;
import types.TypeBool;

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

    }

    @Override
    public IType typecheck(Environment<IType> env) throws TypeError {
        IType condType = condition.typecheck(env);

        if (condType instanceof TypeBool)
            return body.typecheck(env);

        throw new TypeError(OPERATOR);
    }
}
