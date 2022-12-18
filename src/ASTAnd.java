public class ASTAnd implements ASTNode {

    private ASTNode lhs;
    private ASTNode rhs;

    public ASTAnd(ASTNode lhs, ASTNode rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public IValue eval(Environment<IValue> env) throws Exception {
        IValue v1 = lhs.eval(env);

        if (v1 instanceof VBool) {
            IValue v2 = rhs.eval(env);
            if (v2 instanceof VBool)
                return new VBool(((VBool) v1).getVal() && ((VBool) v2).getVal());
        }

        throw new Exception("Invalid argument to '&&' operator");
    }

    @Override
    public void compile(CodeBlock c, Environment<IValue> env) {

    }
}
