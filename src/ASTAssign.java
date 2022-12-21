public class ASTAssign implements ASTNode {

    ASTNode exp;

    public ASTAssign(ASTNode exp) {
        this.exp = exp;
    }

    @Override
    public IValue eval(Environment<IValue> env) throws Exception {
        return null;
    }

    @Override
    public void compile(CodeBlock c, Environment<IValue> env) {

    }
}
