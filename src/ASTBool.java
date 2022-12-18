public class ASTBool implements ASTNode {

    private boolean val;

    public ASTBool(boolean val) {
        this.val = val;
    }

    @Override
    public IValue eval(Environment<IValue> env) throws Exception {
        return new VBool(val);
    }

    @Override
    public void compile(CodeBlock c, Environment<IValue> env) {

    }
}
