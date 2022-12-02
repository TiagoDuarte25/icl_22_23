
public class Bind  implements Type {

    private String id;
    private ASTNode node;

    public Bind(String id, ASTNode node) {
        this.id = id;
        this.node = node;
    }

    public String getId() {
        return id;
    }

    public ASTNode getNode() {
        return node;
    }
}
