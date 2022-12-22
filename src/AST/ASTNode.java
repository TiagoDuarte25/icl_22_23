package AST;

import Environment.*;
import exceptions.*;
import types.*;
import util.*;
import values.*;
public interface ASTNode {

    IValue eval(Environment<IValue> env);
    void compile(CodeBlock c, Environment<IValue> env);

    IType typecheck(Environment<IType> env) throws TypeError;

}

