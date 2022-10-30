import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class ICLCompiler {
    public static void main(String	args[]) {	
        FileInputStream in = null;
        try {
            in = new FileInputStream(args[0]);
        } catch (FileNotFoundException e1) {
            System.out.println("File Not Found");
            e1.printStackTrace();
        }
        Parser parser = new Parser(in);	
        CodeBlock code = new CodeBlock();
        File header = new File("../Header.j");
        try	{
        ASTNode	ast	= parser.Start();
        ast.compile(code);
        code.dump((new PrintStream(header)));
        } catch	(Exception	e)	{	
            System.out.println	("Syntax	Error!");
            System.out.println(e.getMessage());	
            parser.ReInit(System.in);	
        }	
        
    }
}