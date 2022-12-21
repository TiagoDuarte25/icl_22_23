public class ASTIf implements ASTNode {

    ASTNode condition, exp1, exp2;

    /*
    public ASTIf(ASTNode condition, ASTNode exp1) {
        this.condition = condition;
        this.exp1 = exp1;
    }*/

    public ASTIf(ASTNode condition, ASTNode exp1, ASTNode exp2) {
        this.condition = condition;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    @Override
    public IValue eval(Environment<IValue> env) throws Exception {
        IValue cond = condition.eval(env);
        if (cond instanceof VBool) {
            IValue exp1 = this.exp1.eval(env);
            if (((VBool) cond).getVal())
                return exp1;
            else {
                if (exp2 != null)
                    return this.exp2.eval(env);
                return null;
            }
        }
        throw new Exception("Invalid if statement");
    }

    @Override
    public void compile(CodeBlock c, Environment<IValue> env) {

    }
}
