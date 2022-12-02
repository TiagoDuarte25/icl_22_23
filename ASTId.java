public class ASTId implements ASTNode{
    
    private String id;
    private final String GET_FIELD_FRAME = "\tgetfield frame_%d/sl Lframe_%d\n";
    private final String EMIT = "\taload 3\n%s";

    public ASTId(String id) {
        this.id = id;
    }

    public int eval(Environment<Type> e) {
        try {
            if(Type instanceof Coordinates)
                return e.find(id);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        return -1;
    }

    @Override
    public void compile(CodeBlock c, Environment<Type> env) {
        int idFrame = env.depth();
        Type coord;
        try {
            coord = env.find(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        int level_shift = idFrame - coord.envDepth();

        String frames = "";
        for(int i = 0; i < level_shift; i++) {
            frames += String.format(GET_FIELD_FRAME, idFrame - i, idFrame -(i+1));
        }
        frames += String.format("\tgetfield frame_%d/%s %s\n", idFrame - level_shift, coord.varId(), "I");
        String res = String.format(EMIT, frames);
        c.emit(res);
    }
}
