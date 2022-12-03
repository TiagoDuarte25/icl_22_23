public class VInt implements IValue {

    private int v;

    public VInt (int v) {
        this.v = v;
    }

    public int getVal() {
        return v;
    }

    @Override
    public String toString() {
        return String.valueOf(v);
    }
}
