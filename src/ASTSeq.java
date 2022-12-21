public class ASTSeq implements ASTNode {

    private ASTNode left, right;

    public ASTSeq(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public IValue eval(Environment<IValue> env) throws Exception {
        left.eval(env);
        return right.eval(env);
    }

    @Override
    public void compile(CodeBlock c, Environment<IValue> env) {

    }
}
