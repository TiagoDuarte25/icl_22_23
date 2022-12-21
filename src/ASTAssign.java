public class ASTAssign implements ASTNode {

    ASTNode left, right;

    public ASTAssign(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public IValue eval(Environment<IValue> env) throws Exception {
        IValue v1 = left.eval(env);

        if (v1 instanceof VCell) {
            IValue v2 = right.eval(env);
            ((VCell) v1).set(v2);
            return v2;
        }

        throw new Exception("Illegal args to ':=' operator");


    }

    @Override
    public void compile(CodeBlock c, Environment<IValue> env) {

    }
}
