import java.util.HashMap;
import java.util.Map;

public class Environment {

    private Environment previousEnv;
    private Map<String, Integer> variables;

    public Environment(Environment previousEnv) {
        this.previousEnv = previousEnv;
        variables = new HashMap<String, Integer>();
    }

    public Environment beginScope() {
        return new Environment(this);
    }

    public Environment endScope() {
        return previousEnv;
    }
    
    public void assoc(String id, int val) {
        variables.put(id, val);
    }

    public int find(String id) throws Exception { 
        if (variables.containsKey(id)) 
            return variables.get(id);
        if (previousEnv == null)
            throw new Exception("Undeclared Identifier");
        return previousEnv.find(id);
    }
}
