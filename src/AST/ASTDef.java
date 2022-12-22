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

    IType[] types;

    public ASTDef(List<Bind> variables, ASTNode node) {
        this.variables = variables;
        this.node = node;
        types = new IType[variables.size()];
    }

    @Override
    public IType typecheck(Environment<IType> e) throws TypeError {
        Environment<IType> env = e.beginScope();
        for (int i = 0; i < variables.size(); i++) {
            types[i] = variables.get(i).getNode().typecheck(env);
            env.assoc(variables.get(i).getId(), types[i]);

        }

        IType nodeType = node.typecheck(env);

        env.endScope();

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
        for(int i = 0; i < variables.size(); i++) {
            c.emit("aload_3");
            String type = "";
            IType v = types[i];
            if (v instanceof TypeInt)
                type = "I";
            if (v instanceof TypeString)
                type = "Ljava/lang/String;";
            if (v instanceof TypeBool)
                type = "Z";
            if (v instanceof TypeRef) {
                type = "L";
                IType t = v;
                while (t instanceof TypeRef) {
                    type += "ref_of_";
                    t = ((TypeRef) t).getType();
                }
                if (t instanceof TypeInt)
                    type += "int;";
                if (t instanceof TypeString)
                    type += "string;";
                if (t instanceof TypeBool)
                    type += "bool;";
            }
            env.assoc(variables.get(i).getId(), new Coordinates(env.depth(), "v"+ i, type));
            variables.get(i).getNode().compile(c, env);

            c.emit(String.format("putfield frame_%d/v%d %s", env.depth()-1, i, type));
        }

        node.compile(c, env);

        endFrame(c, env);

        createFrameFile(c, env);

        env.endScope();
    }

    private void startFrame(CodeBlock c, Environment<IValue> env) {
        int frameId = env.depth()-1;

        if(frameId == 0) {
            c.emit("aconst_null");
            c.emit("astore_3");
        }
        c.emit(String.format("\n\t\t\tnew frame_%d",frameId));
        c.emit("dup");
        c.emit(String.format("invokespecial frame_%d/<init>()V", frameId));
        c.emit("dup");
        c.emit("aload_3");
        if (frameId == 0)
            c.emit(String.format("putfield frame_%d/sl Ljava/lang/Object;", frameId));
        else
            c.emit(String.format("putfield frame_%d/sl Lframe_%d;", frameId, frameId - 1));
        c.emit("astore_3");

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

    private void createFrameFile(CodeBlock c, Environment<IValue> env) {
        int frameId = env.depth()-1;

        c.emitI(String.format("\n.class public frame_%d", frameId));
        c.emitI(".super java/lang/Object");
        if (frameId == 0)
            c.emitI(".field public sl Ljava/lang/Object;");
        else
            c.emitI(String.format(".field public sl Lframe_%d;", frameId-1));

        for(int i = 0; i < variables.size(); i++) {
            try {
                String type = ((Coordinates) env.find(variables.get(i).getId())).type();
                c.emitI(String.format(".field public v%d %s", i, type));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        c.emitI("\n.method public <init>()V");
        c.emitI("\taload_0");
        c.emitI("\tinvokenonvirtual java/lang/Object/<init>()V");
        c.emitI("\treturn");
        c.emitI(".end method");


        FileOutputStream output = null;
        try {
            output = new FileOutputStream(String.format("frame_%d.j", frameId));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        c.dumpFrames(new PrintStream(output));
    }
}
