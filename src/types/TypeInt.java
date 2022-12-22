package types;

public class TypeInt implements IType {

    public TypeInt() {}

    @Override
    public boolean equalsType(IType type) {
        return type instanceof TypeInt;
    }

    @Override
    public String toStr() {
        return "Integer Type";
    }
}
