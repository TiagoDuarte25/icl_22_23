import java.util.HashMap;
import java.util.Map;

public class Environment<IValue> {

    private Environment<IValue> previousEnv;
    private Map<String, IValue> variables;

    public Environment(Environment<IValue> previousEnv) {
        this.previousEnv = previousEnv;
        variables = new HashMap<String, IValue>();
    }

    public Environment<IValue> beginScope() {
        return new Environment(this);
    }

    public Environment<IValue> endScope() {
        return previousEnv;
    }

    public int depth() {
        if (previousEnv == null)
            return 1;
        return previousEnv.depth() + 1;
    }

    public void assoc(String id, IValue bind) {
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

    public IValue find(String id) throws Exception {
        if (variables.containsKey(id))
            return variables.get(id);
        if (previousEnv == null)
            throw new Exception("Undeclared Identifier");
        return previousEnv.find(id);
    }
}
