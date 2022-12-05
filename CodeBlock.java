import java.io.PrintStream;

class	CodeBlock	{	
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
    + "       .limit locals  1 \n"
    + "       .limit stack 256\n"
    + "\n"
    + "       ; setup local variables:\n"
    + "\n"
    + "       ;    1 - the PrintStream object held in java.lang.System.out\n"
    + "\n"
    + "       getstatic java/lang/System/out Ljava/io/PrintStream;\n"
    + "\n"
    + "       ; place bytecodes here\n"
    + "\n"
    + "       ; START    (4+5)*(8*2) = \n";

    private static final String COMPILE_END = "       ; END \n"
    + "\n"
    + "\n"
    + "       ; convert to String;\n"
    + "       invokestatic java/lang/String/valueOf(I)Ljava/lang/String;\n"
    + "\n"
    + "       ; call println \n"
    + "       invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V\n"
    + "\n"
    + "       return\n"
    + "\n"
    + ".end method\n";;
    
    CodeBlock () {
        code = new String[10000];
        init = new String[10000];
        pc = 0;
        pi = 0;
    }
    
    void emit(String opcode){	
        code[pc++] = opcode;	
    }

    void emitI(String opcode) {init[pi++] = opcode;}

    void dump(PrintStream f)	{ //	dumps	code	to	f

        f.print(COMPILE_START);
            for(int i = 0; init[i] != null; i++) {
                f.println(init[i]);
            }
        f.println(START);
        
        for(int i = 0; code[i] != null; i++) {
            f.println("\t\t\t" + code[i]);
        }

        f.print(COMPILE_END);
    }	
}
