public class ICLInterpreter {
    
    /** Main entry point. */
  public static void main(String args[]) {
    Parser parser = new Parser(System.in);
    ASTNode exp;

    while (true) {
    try {
    exp = parser.Start();
    System.out.println( exp.eval(new Environment(null)) );
    } catch (Exception e) {
      System.out.println ("Syntax Error!");
      parser.ReInit(System.in);
    }
    }
  }
}
