public class ASTUMinus implements ASTNode {

        ASTNode lhs;
    
        public int eval(Environment e)
        { 
            int val = lhs.eval(e);

            return val*-1; 
        }
        
        public ASTUMinus(ASTNode lhs)
        {
	        this.lhs = lhs;
        }

        @Override
        public void compile(CodeBlock c) {
                c.emit("ineg "+ lhs);
        }
}
