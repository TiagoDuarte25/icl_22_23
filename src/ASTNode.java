import exceptions.TypeError;
import types.IType;

public interface ASTNode {

    IValue eval(Environment<IValue> env);
    void compile(CodeBlock c, Environment<IValue> env);

    IType typecheck(Environment<IType> env) throws TypeError;
	
}

