package AST;

import exceptions.*;
import types.*;
import util.*;
import values.*;
import Environment.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;

public class ASTDef implements ASTNode {

    List<Bind> variables;
    ASTNode node;

    public ASTDef(List<Bind> variables, ASTNode node) {
        this.variables = variables;
        this.node = node;
    }

    @Override
    public IType typecheck(Environment<IType> env) throws TypeError {
        Environment<IType> envLocal = env.beginScope();
        for (Bind variable : variables) {
            IType varType = variable.getNode().typecheck(env);
            envLocal.assoc(variable.getId(), varType);
        }

        IType nodeType = node.typecheck(envLocal);

        envLocal.endScope();

        return nodeType;
    }

    @Override
    public IValue eval(Environment<IValue> e) {
        Environment<IValue> env = e.beginScope();

        for (Bind variable : variables) {
            env.assoc(variable.getId(), variable.getNode().eval(env));
        }
        IValue val;

        val = node.eval(env);

        env.endScope();
        return val;
    }
    @Override
    public void compile(CodeBlock c, Environment<IValue> e) {
        Environment<IValue> env = e.beginScope();

        startFrame(c, env);

        c.emit("aload_3");

        for(int i = 0; i < variables.size(); i++) {
            env.assoc(variables.get(i).getId(), new Coordinates(env.depth(), "v"+ i));
            variables.get(i).getNode().compile(c, env);
            String type = "";
            if (variables.get(i).getNode().eval(env) instanceof VInt)
                type = "I";
            else if (variables.get(i).getNode().eval(env) instanceof VString)
                type = "Ljava/lang/String;";
            c.emit(String.format("putfield frame_%d/v%d %s", env.depth()-1, i, type));
        }

        node.compile(c, env);

        endFrame(c, env);

        env.endScope();
    }

    private void startFrame(CodeBlock c, Environment<IValue> env) {
        int frameId = env.depth()-1;

        c.emitI(String.format("\n.class public frame_%d", frameId));
        c.emitI(".super java/lang/Object");

        if(frameId == 0) {
            c.emit("aconst_null");
            c.emit("astore_3");
        }
        c.emit(String.format("\n\t\t\tnew frame_%d",frameId));
        c.emit("dup");
        c.emit(String.format("invokespecial frame_%d/<init>()V", frameId));
        c.emit("dup");
        c.emit("aload_3");
        if (frameId == 0) {
            c.emit(String.format("putfield frame_%d/sl Ljava/lang/Object;", frameId));
            c.emitI(".field public sl Ljava/lang/Object;");
        } else {
            c.emit(String.format("putfield frame_%d/sl Lframe_%d;", frameId, frameId - 1));
            c.emitI(String.format(".field public sl Lframe_%d;", frameId-1));
        }
        c.emit("astore_3");
        for(int i = 0; i < variables.size(); i++) {
            String type = "";
            if (variables.get(i).getNode().eval(env) instanceof VInt)
                type = "I";
            else if (variables.get(i).getNode().eval(env) instanceof VString)
                type = "Ljava/lang/String";
            c.emitI(String.format(".field public v%d %s", i, type));
        }

        c.emitI("\n.method public <init>()V");
        c.emitI("\taload_0");
        c.emitI("\tinvokenonvirtual java/lang/Object/<init>()V");
        c.emitI("\treturn");
        c.emitI(".end method");

        createFrameFile(c, frameId);
    }

    private void endFrame(CodeBlock c, Environment<IValue> env) {
        int frameId = env.depth() - 1;
        c.emit("aload_3");
        if (frameId == 0)
            c.emit(String.format("getfield frame_%d/sl Ljava/lang/Object;", frameId));
        else
            c.emit(String.format("getfield frame_%d/sl Lframe_%d;", frameId, frameId - 1));
        c.emit("astore_3");

    }

    private void createFrameFile(CodeBlock code, int frameId) {
        FileOutputStream output = null;
        try {
            output = new FileOutputStream(String.format("frame_%d.j", frameId));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        code.dumpFrames(new PrintStream(output));
    }
}
