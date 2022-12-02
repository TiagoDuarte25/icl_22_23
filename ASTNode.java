public interface ASTNode {

    int eval(Environment<Type> env);
    void compile(CodeBlock c, Environment<Type> env);
	
}

