package AST;

import exceptions.*;
import types.*;
import util.*;
import values.*;
import Environment.*;

public class ASTAssign implements ASTNode {

    private static final String OPERATOR = ":=";

    ASTNode ref, expAssigned;

    private IType type;

    public ASTAssign(ASTNode ref, ASTNode expAssigned) {
        this.ref = ref;
        this.expAssigned = expAssigned;
    }

    @Override
    public IValue eval(Environment<IValue> env) {
        IValue vId = ref.eval(env);
        IValue vExp = expAssigned.eval(env);

        ((VCell) vId).set(vExp);

        return vExp;
    }

    @Override
    public void compile(CodeBlock c, Environment<IValue> env) {
        ref.compile(c, env);
        expAssigned.compile(c, env);
        c.emit(String.format("getfield ref_of_%s/v %s", getType(type), getJVMType(type)));
    }

    @Override
    public IType typecheck(Environment<IType> env) throws TypeError {
        type = ref.typecheck(env);
        IType tExp = expAssigned.typecheck(env);


        if (type instanceof TypeRef) {
            IType refType = ((TypeRef) type).getType();

            /*if (tExp.equalsType(refType)) {
                return refType;
            }*/

            return refType;
        }

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
