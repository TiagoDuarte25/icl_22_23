import exceptions.TypeError;
import types.IType;
import types.TypeInt;

public class ASTSub implements ASTNode {

    private static final String OPERATOR = "-";

    private ASTNode lhs, rhs;
    
    public IValue eval(Environment<IValue> e) {
        IValue v1 = lhs.eval(e);
        IValue v2 = rhs.eval(e);

        return new VInt(((VInt) v1).getVal() - ((VInt) v2).getVal());
    }

    public ASTSub(ASTNode l, ASTNode r)
    {
    lhs = l; rhs = r;
    }

    @Override
    public void compile(CodeBlock c, Environment<IValue> env) {
        lhs.compile(c, env);
        rhs.compile(c, env);
        c.emit("isub");
    }

    @Override
    public IType typecheck(Environment<IType> env) throws TypeError {
        IType tLeft = lhs.typecheck(env);
        IType tRight = rhs.typecheck(env);

        if (tLeft instanceof TypeInt && tRight instanceof TypeInt)
            return tLeft;

        throw new TypeError(OPERATOR);
    }
}
