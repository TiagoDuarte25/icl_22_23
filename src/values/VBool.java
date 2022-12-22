package values;
public class VBool implements IValue {

    private boolean v;

    public VBool(boolean v) {
        this.v = v;
    }

    public boolean getVal() {
        return v;
    }

    @Override
    public String toString() {
        return String.valueOf(v);
    }
}
