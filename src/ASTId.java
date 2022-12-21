import exceptions.TypeError;
import types.IType;

public class ASTId implements ASTNode{

    private String id;
    private final String GET_FIELD_FRAME = "\t\t\tgetfield frame_%d/sl Lframe_%d;";
    private final String EMIT = "\n\t\t\taload_3\n%s";

    public ASTId(String id) {
        this.id = id;
    }

    public IValue eval(Environment<IValue> e) {
        try {
            return e.find(id);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return null;
    }

    @Override
    public void compile(CodeBlock c, Environment<IValue> env) {
        int idFrame = env.depth() - 1;
        Coordinates coord ;
        try {
            coord = (Coordinates) env.find(id);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        int level_shift = idFrame - coord.envDepth();
        String frames = "";
        for(int i = 0; i < level_shift; i++) {
            frames += String.format(GET_FIELD_FRAME, idFrame - i, idFrame - (i+1));
        }
        frames += String.format("\n\t\t\tgetfield frame_%d/%s %s\n", (idFrame - level_shift), coord.varId(), "I");
        String res = String.format(EMIT, frames);
        c.emit(res);

    }

    @Override
    public IType typecheck(Environment<IType> env) throws TypeError {
        try {
            return env.find(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
