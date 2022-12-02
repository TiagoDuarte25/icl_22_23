public interface ASTNode {

    int eval(Environment env);
    void compile(CodeBlock c);
	
}

