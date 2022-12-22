package types;

public class TypeRef implements IType {

    private IType t;

    public TypeRef(IType t) {
        this.t = t;
    }

    public IType getType() {
        return t;
    }

    @Override
    public boolean equalsType(IType type) {
        return type instanceof TypeRef;
    }

    @Override
    public String toStr() {
        return "Reference type";
    }
}
