package types;

public class TypeBool implements IType {

    public TypeBool() {}

    @Override
    public boolean equalsType(IType type) {
        return type instanceof TypeBool;
    }

    @Override
    public String toStr() {
        return "Boolean Type";
    }
}
