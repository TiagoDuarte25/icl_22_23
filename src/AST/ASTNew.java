package AST;

import exceptions.*;
import types.*;
import util.*;
import values.*;
import Environment.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class ASTNew implements ASTNode {

    ASTNode expr;

    private IType type;

    public ASTNew(ASTNode expr) {
        this.expr = expr;
    }

    @Override
    public IValue eval(Environment<IValue> env) {
        IValue v1 = expr.eval(env);
        return new VCell(v1);
    }

    @Override
    public void compile(CodeBlock c, Environment<IValue> env) {
        String type = getType(this.type);
        String typeJ = getJVMType(this.type);
        c.emitRef(String.format(".class public ref_of_%s", type));
        c.emitRef(".super java/lang/Object");
        c.emitRef(String.format(".field public v %s", typeJ));
        c.emitRef("\n.method public <init>()V");
        c.emitRef("\taload_0");
        c.emitRef("\tinvokenonvirtual java/lang/Object/<init>()V");
        c.emitRef("\treturn");
        c.emitRef(".end method");


        FileOutputStream output = null;
        try {
            output = new FileOutputStream(String.format("ref_of_%s.j", type));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        c.dumpRefs(new PrintStream(output));

        compileToHeader(c, env, type, typeJ);
    }

    @Override
    public IType typecheck(Environment<IType> env) throws TypeError {
        type = expr.typecheck(env);
        return new TypeRef(type);
    }

    private String getType(IType type) {
        String typeName = "";
        if (type instanceof TypeInt)
            return "int";
        if (type instanceof TypeBool)
            return "bool";
        if (type instanceof TypeString)
            return "string";
        if (type instanceof TypeRef) {
            IType i = type;
            while (i instanceof TypeRef) {
                typeName += "ref_of_";
                i = ((TypeRef) i).getType();
            }
            typeName += getType(i);
            return typeName;
        }
        return "";
    }

    private String getJVMType(IType type) {
        String typeName = "";
        if (type instanceof TypeInt)
            return "I";
        if (type instanceof TypeBool)
            return "Z";
        if (type instanceof TypeString)
            return "Ljava/lang/String;";
        if (type instanceof TypeRef) {
            IType i = type;
            typeName += "L";
            while (i instanceof TypeRef) {
                typeName += "ref_of_";
                i = ((TypeRef) i).getType();
            }
            typeName += getType(i);
            typeName += ";";
            return typeName;
        }
        return "";
    }

    private void compileToHeader(CodeBlock c, Environment<IValue> env, String className, String typeName) {
        c.emit(String.format("new ref_of_%s", className));
        c.emit("dup");
        c.emit(String.format("invokespecial ref_of_%s/<init>()V", className));
        c.emit("dup");
        expr.compile(c, env);
        c.emit(String.format("putfield ref_of_%s/v %s", className, typeName));
    }
}
