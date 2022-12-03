import java.util.Iterator;
import java.util.List;

public class ASTDef implements ASTNode {

    List<Bind> variables;
    ASTNode node;

    public ASTDef(List<Bind> variables, ASTNode node) {
        this.variables = variables;
        this.node = node;
    }

    @Override
    public IValue eval(Environment<IValue> env) throws Exception {
        env.beginScope();
        for(int i = 0; i < variables.size(); i++) {
            env.assoc(variables.get(i).getId(), variables.get(i).getNode().eval(env));
        }
        IValue val = node.eval(env);
        env.endScope();
        return val;
    }
    @Override
    public void compile(CodeBlock c, Environment<IValue> env) {

        env.beginScope();

        startFrame(c, env);

        c.emit("aload 3");

        for(int i = 0; i < variables.size(); i++) {
            env.assoc(variables.get(i).getId(), new Coordinates(env.depth()-1, "v"+ i));
            variables.get(i).getNode().compile(c, env);
            c.emit(String.format("putfield frame_%d/v%d I", env.depth()- 1, i));
        }

        node.compile(c, env);

        endFrame(c, env);

        env.endScope();
    }

    private void startFrame(CodeBlock c, Environment<IValue> env) {
        int frameId = env.depth()-1;
        c.emit(String.format("\nnew frame_%d",frameId));
        c.emit("dup");
        c.emit(String.format("invokespecial frame_%d/<init>()V", frameId));
        c.emit("dup");
        c.emit("aload 3");
        if (frameId == 0)
            c.emit(String.format("putfield frame_%d/sl Ljava/lang/Object;", frameId));
        else
            c.emit(String.format("putfield frame_%d/sl Lframe_%d;", frameId, frameId - 1));
        c.emit("astore 3");
    }

    private void endFrame(CodeBlock c, Environment<IValue> env) {
        int frameId = env.depth() - 1;
        c.emit("aload 3");
        if (frameId == 0)
            c.emit(String.format("getfield frame_%d/sl Ljava/lang/Object;", frameId));
        else
            c.emit(String.format("getfield frame_%d/sl Lframe_%d;", frameId, frameId - 1));
        c.emit("astore 3");
    }
}
