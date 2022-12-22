package AST;

import exceptions.*;
import types.*;
import util.*;
import values.*;
import Environment.*;
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
        c.emitI(String.format("\n.class public ref_of_%s", getType(this.type)));
        c.emitI(".super java/lang/Object");

        c.emitI(String.format(".field public v %s", getJVMType(this.type)));
        c.emitI("\n.method public <init>()V");
        c.emitI("\taload_0");
        c.emitI("\tinvokenonvirtual java/lang/Object/<init>()V");
        c.emitI("\treturn");
        c.emitI(".end method");

    }

    @Override
    public IType typecheck(Environment<IType> env) throws TypeError {
        type = expr.typecheck(env);
        System.out.println("TYPE IN TYPECHK: " + type);
        return new TypeRef(type);
    }

    private String getType(IType type) {
        StringBuilder typeName = new StringBuilder();
        if (type instanceof TypeInt)
            return "int";
        if (type instanceof TypeBool)
            return "bool";
        if (type instanceof TypeString)
            return "string";
        if (type instanceof TypeRef) {
            IType i = type;
            while (i instanceof TypeRef) {
                typeName.append("ref_of_");
                i = ((TypeRef) type).getType();
            }
            i = ((TypeRef) type).getType();
            typeName.append(getType(i));
            return typeName.toString();
        }
        return "";
    }

    private String getJVMType(IType type) {
        StringBuilder typeName = new StringBuilder();
        if (type instanceof TypeInt)
            return "I";
        if (type instanceof TypeBool)
            return "Z";
        if (type instanceof TypeString)
            return "Ljava/lang/String;";
        if (type instanceof TypeRef) {
            IType i = type;
            while (i instanceof TypeRef) {
                typeName.append("ref_of_");
                i = ((TypeRef) type).getType();
            }
            i = ((TypeRef) type).getType();
            typeName.append(getJVMType(i));
            return typeName.toString();
        }
        return "";
    }
}
