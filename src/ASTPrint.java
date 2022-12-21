import exceptions.TypeError;
import types.IType;

public class ASTPrint implements ASTNode {
    private ASTNode body;
    private boolean isLine;

    public ASTPrint(ASTNode body, boolean isLine) {
        this.body = body;
        this.isLine = isLine;
    }

    @Override
    public IValue eval(Environment<IValue> env) {
        IValue v1 = body.eval(env);
        if (isLine)
            System.out.println(v1);
        else
            System.out.print(v1);

        return v1;
    }

    @Override
    public void compile(CodeBlock c, Environment<IValue> env) {

    }

    @Override
    public IType typecheck(Environment<IType> env) throws TypeError {
        return body.typecheck(env);
    }
}
