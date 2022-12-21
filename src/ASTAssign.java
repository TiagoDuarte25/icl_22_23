import exceptions.TypeError;
import types.IType;
import types.TypeRef;

public class ASTAssign implements ASTNode {

    private static final String OPERATOR = ":=";

    ASTNode ref, expAssigned;

    public ASTAssign(ASTNode ref, ASTNode expAssigned) {
        this.ref = ref;
        this.expAssigned = expAssigned;
    }

    @Override
    public IValue eval(Environment<IValue> env) {
        IValue vId = ref.eval(env);
        IValue vExp = expAssigned.eval(env);

        ((VCell) vId).set(vExp);

        return vExp;
    }

    @Override
    public void compile(CodeBlock c, Environment<IValue> env) {

    }

    @Override
    public IType typecheck(Environment<IType> env) throws TypeError {
        IType tRef = ref.typecheck(env);
        IType tExp = expAssigned.typecheck(env);

        if (tRef instanceof TypeRef) {
            IType refType = ((TypeRef) tRef).getType();

            if (tExp.getClass().equals(refType.getClass()))
                return refType;
        }

        throw new TypeError(OPERATOR);
    }
}
