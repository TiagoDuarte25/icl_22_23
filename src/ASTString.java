import exceptions.TypeError;
import types.IType;
import types.TypeString;

import javax.lang.model.util.Types;

public class ASTString implements ASTNode {

    private String text;

    public ASTString(String text) {
        this.text = text;
    }

    @Override
    public IValue eval(Environment<IValue> env) {
        return new VString(text.replaceAll("\\p{P}",""));
    }

    @Override
    public void compile(CodeBlock c, Environment<IValue> env) {
        c.emit("ldc \"" + text + "\"");
    }

    @Override
    public IType typecheck(Environment<IType> env) throws TypeError {
        return new TypeString();
    }
}
