import java.io.*;
import AST.*;
import types.IType;
import util.*;
import values.IValue;
import Environment.*;

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
        try	{
            ASTNode ast	= parser.Start();
            ast.typecheck(new Environment<IType>(null));
            ast.compile(code, new Environment<IValue>(null));
            FileOutputStream output = new FileOutputStream("Header.j");
            code.dump(new PrintStream(output));
        } catch	(Exception	e)	{	
            System.out.println	("Syntax Error!");
            System.out.println(e.getMessage());	
            parser.ReInit(System.in);	
        }	
        
    }
}