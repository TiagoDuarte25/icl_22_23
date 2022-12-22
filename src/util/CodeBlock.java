package util;
import java.io.*;

public class CodeBlock	{
    String	code[];
    String init[];
    int	pc, pi;

    private static final String COMPILE_START = ".class public Header\n"
    + ".super java/lang/Object\n"
    + "\n"
    + ";\n"
    + "; standard initializer\n"
    + ".method public <init>()V\n"
    + "   aload_0\n"
    + "   invokenonvirtual java/lang/Object/<init>()V\n"
    + "   return\n"
    + ".end method\n"
    + "\n";
    private static final String START = "\n"
    + ".method public static main([Ljava/lang/String;)V\n"
    + "\n"
    + "       ; set limits used by this method\n"
    + "       .limit locals  10 \n"
    + "       .limit stack 256\n"
    + "\n"
    + "       ; setup local variables:\n"
    + "\n"
    + "       ; place bytecodes here\n"
    + "\n"
    + "       ; START = \n";

    private static final String COMPILE_END = "       ; END \n"
    + "\n"
    + "       return\n"
    + "\n"
    + ".end method\n";;
    
    public CodeBlock () {
        code = new String[10000];
        init = new String[10000];
        pc = 0;
        pi = 0;
    }
    
    public void emit(String opcode){
        code[pc++] = opcode;	
    }

    public void emitI(String opcode) {init[pi++] = opcode;}

    public void dump(PrintStream f)	{ //	dumps	code	to	f

        f.print(COMPILE_START);

        f.println(START);

        for(int i = 0; code[i] != null; i++) {
            f.println("\t\t\t" + code[i]);
        }

        f.print(COMPILE_END);
    }

    public void dumpFrames(PrintStream f) {

        for(int i = 0; init[i] != null; i++) {
            f.println(init[i]);
        }

        init = new String[10000];
    }
}
