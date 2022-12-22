package AST;

import exceptions.*;
import types.*;
import Environment.*;
import util.*;
import values.*;

public class ASTDeref implements ASTNode {

    private static final String OPERATOR = "!";

    ASTNode exp;

    private IType type;

    public ASTDeref(ASTNode exp) {
        this.exp = exp;
    }

    @Override
    public IValue eval(Environment<IValue> env) {
        IValue v1 = exp.eval(env);
        return ((VCell) v1).getVal();
    }

    @Override
    public void compile(CodeBlock c, Environment<IValue> env) {
        exp.compile(c, env);
        c.emit(String.format("getfield ref_of_%s/v %s", getType(type), getJVMType(type)));
    }

    @Override
    public IType typecheck(Environment<IType> env) throws TypeError {
        type = exp.typecheck(env);

        if (type instanceof TypeRef)
            return ((TypeRef) type).getType();

        throw new TypeError(OPERATOR);
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
            IType i = ((TypeRef) type).getType();
            while (i instanceof TypeRef) {
                System.out.println("IType: " + i);
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
        IType i = ((TypeRef) type).getType();
        if (i instanceof TypeInt)
            return "I";
        if (i instanceof TypeBool)
            return "Z";
        if (i instanceof TypeString)
            return "Ljava/lang/String;";
        if (i instanceof TypeRef) {

            typeName += "L";
            while (i instanceof TypeRef) {
                System.out.println("IJVM: " + i);
                typeName += "ref_of_";
                i = ((TypeRef) i).getType();
            }
            typeName += getType(i);
            typeName += ";";
            return typeName;
        }
        return "";
    }

}
