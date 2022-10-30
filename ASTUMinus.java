public class ASTUMinus implements ASTNode {

        ASTNode lhs;
    
        public int eval()
        { 
            int val = lhs.eval();

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
