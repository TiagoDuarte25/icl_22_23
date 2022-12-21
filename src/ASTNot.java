import exceptions.TypeError;
import types.IType;
import types.TypeBool;

public class ASTNot implements ASTNode {

    private static final String OPERATOR = "~";

    private ASTNode exp;

    public ASTNot(ASTNode exp) {
        this.exp = exp;
    }

    @Override
    public IValue eval(Environment<IValue> env){
        IValue evalExp = exp.eval(env);
        return new VBool( !((VBool) evalExp).getVal());
    }

    @Override
    public void compile(CodeBlock c, Environment<IValue> env) {

    }

    @Override
    public IType typecheck(Environment<IType> env) throws TypeError {
        IType expType = exp.typecheck(env);

        if (expType instanceof TypeBool)
            return expType;

        throw new TypeError(OPERATOR);
    }


}
