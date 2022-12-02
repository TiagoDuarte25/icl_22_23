public class ASTMult implements ASTNode {

    ASTNode lhs, rhs;
    
            public int eval(Environment e)
            { 
        int v1 = lhs.eval(e);
        int v2 = rhs.eval(e);
            return v1*v2; 
        }
        
            public ASTMult(ASTNode l, ASTNode r)
            {
            lhs = l; rhs = r;
            }

            @Override
            public void compile(CodeBlock c, Environment env) {
                lhs.compile(c, env);
                rhs.compile(c, env);
                c.emit("imul");
            }
    }
    