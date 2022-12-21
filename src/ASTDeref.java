public class ASTDeref implements ASTNode {

    ASTNode exp;

    public ASTDeref(ASTNode exp) {
        this.exp = exp;
    }

    @Override
    public IValue eval(Environment<IValue> env) throws Exception {
        IValue v1 = exp.eval(env);
        IValue res;
        if(v1 instanceof VCell) {
            res = ((VCell) v1).getVal();
            return res;
        }
        throw new Exception("Illegal argument type to ! operator");
    }

    @Override
    public void compile(CodeBlock c, Environment<IValue> env) {

    }
}
