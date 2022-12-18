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

        switch(op) {
            case "==":
                if (v1 instanceof VInt && v2 instanceof VInt)
                    return new VBool(((VInt) v1).getVal() == ((VInt) v2).getVal());
                if (v1 instanceof VBool && v2 instanceof VBool)
                    return new VBool(((VBool) v1).getVal() == ((VBool) v2).getVal());
                break;
            case "<":
                if (v1 instanceof VInt && v2 instanceof VInt)
                    return new VBool(((VInt) v1).getVal() < ((VInt) v2).getVal());
                break;
            case ">":
                if (v1 instanceof VInt && v2 instanceof VInt)
                    return new VBool(((VInt) v1).getVal() > ((VInt) v2).getVal());
                break;
            case "<=":
                if (v1 instanceof VInt && v2 instanceof VInt)
                    return new VBool(((VInt) v1).getVal() <= ((VInt) v2).getVal());
                break;
            case ">=":
                if (v1 instanceof VInt && v2 instanceof VInt)
                    return new VBool(((VInt) v1).getVal() >= ((VInt) v2).getVal());
                break;
            default: break;
        }


        throw new Exception("Invalid arguments to '"+ op +"' operator");
    }

    @Override
    public void compile(CodeBlock c, Environment<IValue> env) {

    }
}
