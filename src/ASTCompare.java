import exceptions.TypeError;
import types.IType;
import types.TypeBool;
import types.TypeInt;

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
    public IValue eval(Environment<IValue> env) {
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

        // TODO - all types
        return new VBool(((VBool) v1).getVal() == ((VBool) v2).getVal());

    }

    @Override
    public void compile(CodeBlock c, Environment<IValue> env) {

    }

    @Override
    public IType typecheck(Environment<IType> env) throws TypeError {
        IType tLeft = lhs.typecheck(env);
        IType tRight = rhs.typecheck(env);

        if ( ( tLeft instanceof TypeInt && tRight instanceof TypeInt ) || ( tLeft.equals(tRight) && op.equals("==") ) )
            return tLeft;

        throw new TypeError(op);
    }
}
