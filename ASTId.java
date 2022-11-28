public class ASTId implements ASTNode{
    
    String id;

    public ASTId(String id) {
        this.id = id;
    }

    public int eval(Environment e) {
        try {
            return e.find(id);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
       
        return -1;
    }

    @Override
    public void compile(CodeBlock c) {
        // TODO Auto-generated method stub

    }
}
