public class VCell implements IValue{

    private IValue v;

    public VCell (IValue v) {
        this.v = v;
    }

    public IValue get() {
        return v;
    }

    void set(IValue	v0)	{v = v0;}

    @Override
    public String toString() {
        return String.valueOf(v);
    }
}
