public class ASTWhile implements ASTNode {
    private ASTNode condition, body;

    public ASTWhile(ASTNode condition, ASTNode body) {
        this.condition = condition;
        this.body = body;
    }

    @Override
    public IValue eval(Environment<IValue> env) throws Exception {
        IValue condVal = condition.eval(env);

        if (condVal instanceof VBool) {
            IValue bodyVal = null;
            while (((VBool) condVal).getVal()) {
                bodyVal = body.eval(env);
                condVal = condition.eval(env);
            }
            return bodyVal;
        }

        throw new Exception("Illegal args for 'while' operator");
    }

    @Override
    public void compile(CodeBlock c, Environment<IValue> env) {

    }
}
