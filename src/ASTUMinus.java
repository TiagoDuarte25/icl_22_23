import exceptions.TypeError;
import types.IType;
import types.TypeInt;

public class ASTUMinus implements ASTNode {

    private static final String OPERATOR = "-";

    private ASTNode v;

    public IValue eval(Environment<IValue> e) {
        IValue val = v.eval(e);
        return new VInt(-((VInt) val).getVal());
    }

    public ASTUMinus(ASTNode lhs)
    {
        this.v = lhs;
    }

    @Override
    public void compile(CodeBlock c, Environment<IValue> env) {
        v.compile(c, env);
        c.emit("ineg");
    }

    @Override
    public IType typecheck(Environment<IType> env) throws TypeError {
        IType vType = v.typecheck(env);
        if (vType instanceof TypeInt)
            return vType;
        throw new TypeError(OPERATOR);
    }
}
