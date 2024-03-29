package AST;

import exceptions.*;
import types.*;
import util.*;
import values.*;
import Environment.*;
public class ASTPrint implements ASTNode {
    private ASTNode body;
    private boolean isLine;

    private IType type;

    public ASTPrint(ASTNode body, boolean isLine) {
        this.body = body;
        this.isLine = isLine;
    }

    @Override
    public IValue eval(Environment<IValue> env) {
        IValue v1 = body.eval(env);
        if (isLine)
            System.out.println(v1);
        else
            System.out.print(v1);

        return v1;
    }

    @Override
    public void compile(CodeBlock c, Environment<IValue> env) {

        c.emit("getstatic java/lang/System/out Ljava/io/PrintStream;");

        body.compile(c, env);

        if (type instanceof TypeInt)
            c.emit("invokestatic java/lang/String/valueOf(I)Ljava/lang/String;");
        if (type instanceof TypeBool)
            c.emit("invokestatic java/lang/String/valueOf(Z)Ljava/lang/String;");
        if (type instanceof TypeRef)
            c.emit("invokestatic java/lang/String/valueOf(Ljava/lang/Object;)Ljava/lang/String;");
        if (isLine)
            c.emit("invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
        else
            c.emit("invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V");
    }
    @Override
    public IType typecheck(Environment<IType> env) throws TypeError {
        type = body.typecheck(env);
        return type;
    }
}
