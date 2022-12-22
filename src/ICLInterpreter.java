import AST.ASTNode;
import Environment.Environment;
import types.IType;
import values.IValue;

public class ICLInterpreter {
    
    /** Main entry point. */
  public static void main(String args[]) {
    Parser parser = new Parser(System.in);
    ASTNode exp;

    while (true) {
      try {
        System.out.print("> ");
        exp = parser.Start();
        exp.typecheck(new Environment<IType>(null));
        exp.eval(new Environment<IValue>(null));
      } catch (Exception e) {
        System.out.println(e.getMessage());
        parser.ReInit(System.in);
      }
    }
  }
}
