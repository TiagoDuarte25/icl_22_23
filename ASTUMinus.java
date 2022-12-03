public class ASTUMinus implements ASTNode {

        ASTNode lhs;
    
        public IValue eval(Environment<IValue> e) throws Exception {
            IValue val = lhs.eval(e);
            if (val instanceof VInt)
                return new VInt(((VInt) val).getVal()*-1);
            if (val instanceof VBool)
                return new VBool(!((VBool) val).getVal());
            throw new Exception("Invalid type");
        }
        
        public ASTUMinus(ASTNode lhs)
        {
	        this.lhs = lhs;
        }

        @Override
        public void compile(CodeBlock c, Environment<IValue> env) {
                c.emit("ineg "+ lhs);
        }
}
