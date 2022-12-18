public interface ASTNode {

    IValue eval(Environment<IValue> env) throws Exception;
    void compile(CodeBlock c, Environment<IValue> env);
	
}

