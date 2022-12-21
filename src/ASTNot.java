public class ASTNot implements ASTNode {

    private ASTNode exp;

    public ASTNot(ASTNode exp) {
        this.exp = exp;
    }

    @Override
    public IValue eval(Environment<IValue> env) throws Exception {
        IValue evalExp = exp.eval(env);
        if (evalExp instanceof VBool)
            return new VBool( !((VBool) evalExp).getVal());

        throw new Exception("Invalid argument to '~' operator");
    }

    @Override
    public void compile(CodeBlock c, Environment<IValue> env) {

    }
}
