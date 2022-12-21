public class ICLInterpreter {
    
    /** Main entry point. */
  public static void main(String args[]) {
    Parser parser = new Parser(System.in);
    ASTNode exp;

    while (true) {
      try {
        exp = parser.Start();
        exp.typecheck(new Environment<>(null));
        exp.eval(new Environment<>(null));
      } catch (Exception e) {
        System.out.println(e.getMessage());
        parser.ReInit(System.in);
      }
    }
  }
}
