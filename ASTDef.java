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
    public IValue eval(Environment<IValue> e) throws Exception {
        Environment env = e.beginScope();
        System.out.printf("env1: %s \n", e.depth());
        System.out.printf("env2: %s \n", env.depth());
        for(int i = 0; i < variables.size(); i++) {
            e.assoc(variables.get(i).getId(), variables.get(i).getNode().eval(e));
        }
        IValue val;
        if(node instanceof ASTDef){
            val = node.eval(env);
        }
        else
            val = node.eval(e);

        env.endScope();
        return val;
    }
    @Override
    public void compile(CodeBlock c, Environment<IValue> e) {

        Environment env = e.beginScope();

        startFrame(c, e);

        c.emit("aload 3");

        for(int i = 0; i < variables.size(); i++) {
            e.assoc(variables.get(i).getId(), new Coordinates(env.depth()-1, "v"+ i));
            variables.get(i).getNode().compile(c, e);
            c.emit(String.format("putfield frame_%d/v%d I", env.depth()- 1, i));
        }

        if(node instanceof ASTDef)
           node.compile(c, env);
        else
            node.compile(c, e);

        endFrame(c, e);

        env.endScope();
    }

    private void startFrame(CodeBlock c, Environment<IValue> env) {
        int frameId = env.depth()-1;

        c.emitI(String.format("\n.class public frame_%d", frameId));
        c.emitI(String.format(".super java/lang/Object"));

        c.emit(String.format("\n\t\t\tnew frame_%d",frameId));
        c.emit("dup");
        c.emit(String.format("invokespecial frame_%d/<init>()V", frameId));
        c.emit("dup");
        c.emit("aload 3");
        if (frameId == 0) {
            c.emit(String.format("putfield frame_%d/sl Ljava/lang/Object;", frameId));
            c.emitI(".field public sl Ljava/lang/Object;");
        } else {
            c.emit(String.format("putfield frame_%d/sl Lframe_%d;", frameId, frameId - 1));
            c.emitI(String.format(".field public sl Lframe_%d;", frameId-1));
        }
        c.emit("astore 3");
        for(int i = 0; i < variables.size(); i++)
            c.emitI(String.format(".field public v%d I", i));

        c.emitI(".method public <init>()V");
        c.emitI("aload_0");
        c.emitI("invokenonvirtual java/lang/Object/<init>()V");
        c.emitI("return");
        c.emitI(".end method");
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
