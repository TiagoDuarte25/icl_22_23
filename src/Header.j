.class public Header
.super java/lang/Object

;
; standard initializer
.method public <init>()V
   aload_0
   invokenonvirtual java/lang/Object/<init>()V
   return
.end method


.method public static main([Ljava/lang/String;)V

       ; set limits used by this method
       .limit locals  10 
       .limit stack 256

       ; setup local variables:

       ; place bytecodes here

       ; START = 

			aconst_null
			astore_3
			
			new frame_0
			dup
			invokespecial frame_0/<init>()V
			dup
			aload_3
			putfield frame_0/sl Ljava/lang/Object;
			astore_3
			aload_3
			sipush 1
			putfield frame_0/v0 I
			aload_3
			
			aload_3

			getfield frame_0/v0 I

			sipush 1
			iadd
			putfield frame_0/v1 I
			getstatic java/lang/System/out Ljava/io/PrintStream;
			
			aload_3

			getfield frame_0/v1 I

			invokestatic java/lang/String/valueOf(I)Ljava/lang/String;
			invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
			aload_3
			getfield frame_0/sl Ljava/lang/Object;
			astore_3
       ; END 

       return

.end method
