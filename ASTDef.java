import java.util.Iterator;
import java.util.List;

public class ASTDef implements ASTNode {

    List<Pair<String, ASTNode>> variables;
    ASTNode node;

    public ASTDef(List<Pair<String, ASTNode>> variables, ASTNode node) {
        this.variables = variables;
        this.node = node;
    }

    @Override
    public int eval(Environment e) {

        Environment env = e;

        env.beginScope();

        Iterator<Pair<String, ASTNode>> it = variables.iterator();
        while(it.hasNext()) {
            Pair<String, ASTNode> pair = it.next();
            env.assoc(pair.getFirst(), pair.getSecond().eval(e));
        }

        int val = node.eval(env);
        env.endScope();
        e = env;
        return val;
    }
    @Override
    public void compile(CodeBlock c) {
        
    }
}
