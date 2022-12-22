package AST;

import types.*;
import util.*;
import values.*;
import Environment.*;

public class ASTBool implements ASTNode {

    private boolean val;

    public ASTBool(boolean val) {
        this.val = val;
    }

    @Override
    public IValue eval(Environment<IValue> env) {
        return new VBool(val);
    }

    @Override
    public void compile(CodeBlock c, Environment<IValue> env) {
        if (val) c.emit("iconst_1");
        else c.emit("iconst_0");
    }

    public IType typecheck(Environment<IType> env) {
        return new TypeBool();
    }
}
