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

        String typeName = getType(this.type);
        if (type instanceof TypeRef) {
            IType i = ((TypeRef) type).getType();
            while (i instanceof TypeRef) {
                typeName += "ref_of_";
                i = ((TypeRef) type).getType();
            }
            i = ((TypeRef) type).getType();
            typeName += getType(i);
        }

        c.emitI(String.format("\n.class public ref_of_%s", typeName));
        c.emitI(".super java/lang/Object");

        String typeJ = "";
        
            c.emitI(String.format(".field public v %s"));
        c.emitI("\n.method public <init>()V");
        c.emitI("\taload_0");
        c.emitI("\tinvokenonvirtual java/lang/Object/<init>()V");
        c.emitI("\treturn");
        c.emitI(".end method");

    }

    @Override
    public IType typecheck(Environment<IType> env) throws TypeError {
         type = expr.typecheck(env);
        return new TypeRef(type);
    }

    private String getType(IType type) {
        if (type instanceof TypeInt)
            return "int";
        if (type instanceof TypeBool)
            return "bool";
        if (type instanceof TypeString)
            return"string";
        return "";
    }
}
