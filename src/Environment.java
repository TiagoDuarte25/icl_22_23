import java.util.HashMap;
import java.util.Map;

public class Environment<T> {

    private Environment<T> previousEnv;
    private Map<String, T> variables;

    public Environment(Environment<T> previousEnv) {
        this.previousEnv = previousEnv;
        variables = new HashMap<String, T>();
    }

    public Environment<T> beginScope() {
        return new Environment<T>(this);
    }

    public Environment<T> endScope() {
        return previousEnv;
    }

    public int depth() {
        if (previousEnv == null)
            return 1;
        return previousEnv.depth() + 1;
    }

    public void assoc(String id, T bind) {
        //if (variables.containsKey(id))
            //throw new Exception("Variable with id " + id + " already exists");
        variables.put(id, bind);
    }

    public T find(String id) throws Exception {
        if (variables.containsKey(id))
            return variables.get(id);
        if (previousEnv == null)
            throw new Exception("Undeclared Identifier");
        return previousEnv.find(id);
    }
}
