import types.IType;
import types.TypeBool;

public class ASTBool implements ASTNode {

    private boolean val;

    public ASTBool(boolean val) {
        this.val = val;
    }

    @Override
    public IValue eval(Environment<IValue> env) {
        return new VBool(val);
    }

    @Override
    public void compile(CodeBlock c, Environment<IValue> env) {

    }

    public IType typecheck(Environment<IType> env) {
        return new TypeBool();
    }
}
