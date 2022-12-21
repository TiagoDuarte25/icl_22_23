package types;

public class TypeString implements IType {

    public TypeString() {}

    @Override
    public String toStr() {
        return "String type";
    }
}
