import exceptions.TypeError;
import types.IType;
import types.TypeRef;

public class ASTNew implements ASTNode {

    ASTNode expr;

    public ASTNew(ASTNode expr) {
        this.expr = expr;
    }

    @Override
    public IValue eval(Environment<IValue> env) {
        IValue v1 = expr.eval(env);
        return new VCell(v1);
    }

    @Override
    public void compile(CodeBlock c, Environment<IValue> env) {

    }

    @Override
    public IType typecheck(Environment<IType> env) throws TypeError {
        IType expType = expr.typecheck(env);
        return new TypeRef(expType);
    }
}
