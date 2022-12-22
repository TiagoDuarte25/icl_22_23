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
			ldc "ola"
			putfield frame_0/v0 Ljava/lang/String;
			getstatic java/lang/System/out Ljava/io/PrintStream;
			
			aload_3

			getfield frame_0/v0 boas

			invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
			aload_3
			getfield frame_0/sl Ljava/lang/Object;
			astore_3
       ; END 

       return

.end method
