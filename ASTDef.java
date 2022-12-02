import java.util.Iterator;
import java.util.List;

public class ASTDef implements ASTNode {

    List<Bind> variables;
    ASTNode node;

    public ASTDef(List<Bind> variables, ASTNode node) {
        this.variables = variables;
        this.node = node;
    }

    @Override
    public int eval(Environment e) {

        Environment env = e;

        env.beginScope();
        for(int i = 0; i < variables.size(); i++) {
            //env.assoc(pair.getId(), pair.getNode().eval(e));
            env.assoc(variables.get(i).getId(), new Coordinates(e.depth(), "v"+ i));
        }

        int val = node.eval(env);
        env.endScope();
        e = env;
        return val;
    }
    @Override
    public void compile(CodeBlock c, Environment env) {
        
    }
}
