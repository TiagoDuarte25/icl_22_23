import java.util.HashMap;
import java.util.Map;

public class Environment<Type> {

    private Environment<Type> previousEnv;
    private Map<String, Type> variables;

    public Environment(Environment<Type> previousEnv) {
        this.previousEnv = previousEnv;
        variables = new HashMap<String, Type>();
    }

    public Environment<Type> beginScope() {
        return new Environment(this);
    }

    public Environment<Type> endScope() {
        return previousEnv;
    }

    public int depth() {
        if (previousEnv == null)
            return 1;
        return previousEnv.depth() + 1;
    }

    public void assoc(String id, Type bind) {
        variables.put(id, bind);
    }
/*
    public int find(String id) throws Exception {
        if (variables.containsKey(id)) 
            return variables.get(id);
        if (previousEnv == null)
            throw new Exception("Undeclared Identifier");
        return previousEnv.find(id);
    }*/

    public Type find(String id) throws Exception {
        if (variables.containsKey(id))
            return variables.get(id);
        if (previousEnv == null)
            throw new Exception("Undeclared Identifier");
        return previousEnv.find(id);
    }
}
