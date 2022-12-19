public class ASTCompare implements ASTNode {

    private ASTNode lhs;
    private ASTNode rhs;

    private String op;

    public ASTCompare(ASTNode lhs, ASTNode rhs, String operator) {
        this.lhs = lhs;
        this.rhs = rhs;
        op = operator;
    }

    @Override
    public IValue eval(Environment<IValue> env) throws Exception {
        IValue v1 = lhs.eval(env);
        IValue v2 = rhs.eval(env);

        if (v1 instanceof VInt && v2 instanceof VInt) {
            switch(op) {
                case "==":
                    return new VBool(((VInt) v1).getVal() == ((VInt) v2).getVal());
                case "<":
                    return new VBool(((VInt) v1).getVal() < ((VInt) v2).getVal());
                case ">":
                    return new VBool(((VInt) v1).getVal() > ((VInt) v2).getVal());
                case "<=":
                    return new VBool(((VInt) v1).getVal() <= ((VInt) v2).getVal());
                case ">=":
                    return new VBool(((VInt) v1).getVal() >= ((VInt) v2).getVal());
                default: break;
            }
        }
        if (v1 instanceof VBool && v2 instanceof VBool && op.equals("=="))
            return new VBool(((VBool) v1).getVal() == ((VBool) v2).getVal());

        throw new Exception("Invalid arguments to '"+ op +"' operator");
    }

    @Override
    public void compile(CodeBlock c, Environment<IValue> env) {

    }
}
