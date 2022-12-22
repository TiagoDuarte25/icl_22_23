package types;

public class TypeString implements IType {

    public TypeString() {}

    @Override
    public boolean equalsType(IType type) {
        return type instanceof TypeString;
    }

    @Override
    public String toStr() {
        return "String type";
    }
}
