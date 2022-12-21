public class ASTNew implements ASTNode {

    ASTNode expr;

    public ASTNew(ASTNode expr) {
        this.expr = expr;
    }

    @Override
    public IValue eval(Environment<IValue> env) throws Exception {
        IValue v1 = expr.eval(env);
        return new VCell(v1);
    }

    @Override
    public void compile(CodeBlock c, Environment<IValue> env) {

    }
}
