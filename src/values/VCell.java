package values;
public class VCell implements IValue{

    private IValue v;

    public VCell (IValue v) {
        this.v = v;
    }

    public IValue getVal() {
        return v;
    }

    public void set(IValue v0)	{v = v0;}

}
