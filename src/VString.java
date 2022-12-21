public class VString implements IValue {

    private String v;

    public VString(String v) {
        this.v = v;
    }

    public String getVal() { return v; }

    @Override
    public String toString() {
        return v;
    }
}
