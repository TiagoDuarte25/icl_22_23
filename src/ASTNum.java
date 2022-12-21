import types.*;

public class ASTNum implements ASTNode {

        int val;

        public IValue eval(Environment<IValue> e) { return new VInt(val); }

        public ASTNum(int n)
        {
        val = n;
        }

        @Override
        public void compile(CodeBlock c, Environment<IValue> env) {
                c.emit("sipush "+ val);
        }

        @Override
        public IType typecheck(Environment<IType> env) {
                return new TypeInt();
        }

}

