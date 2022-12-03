public class ASTPlus implements ASTNode {

ASTNode lhs, rhs;

        public IValue eval(Environment<IValue> e) throws Exception {
            IValue v1 = lhs.eval(e);
            IValue v2 = rhs.eval(e);
            if (v1 instanceof VInt && v2 instanceof VInt)
                return new VInt(((VInt) v1).getVal() + ((VInt) v2).getVal());
            throw new Exception("Illegal arguments '+' operation");
	}
    
        public ASTPlus(ASTNode l, ASTNode r)
        {
		lhs = l; rhs = r;
        }

        @Override
        public void compile(CodeBlock c, Environment<IValue> env) {
                lhs.compile(c, env);
                rhs.compile(c, env);
                c.emit("iadd");
        }
}

