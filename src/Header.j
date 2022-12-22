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
			iconst_1
			putfield frame_0/v0 Z
			
			new frame_1
			dup
			invokespecial frame_1/<init>()V
			dup
			aload_3
			putfield frame_1/sl Lframe_0;
			astore_3
			aload_3
			
			aload_3
			getfield frame_1/sl Lframe_0;
			getfield frame_0/v0 Z

			putfield frame_1/v0 Z
			
			new frame_2
			dup
			invokespecial frame_2/<init>()V
			dup
			aload_3
			putfield frame_2/sl Lframe_1;
			astore_3
			aload_3
			sipush 2
			sipush 2
			isub
			ifgt L1
			iconst_0
			goto L2
			L1:
			iconst_1
			L2:
			putfield frame_2/v0 Z
			getstatic java/lang/System/out Ljava/io/PrintStream;
			sipush 3
			invokestatic java/lang/String/valueOf(I)Ljava/lang/String;
			invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
			getstatic java/lang/System/out Ljava/io/PrintStream;
			
			aload_3

			getfield frame_2/v0 Z

			invokestatic java/lang/String/valueOf(Z)Ljava/lang/String;
			invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
			aload_3
			getfield frame_2/sl Lframe_1;
			astore_3
			getstatic java/lang/System/out Ljava/io/PrintStream;
			
			aload_3

			getfield frame_1/v0 Z

			invokestatic java/lang/String/valueOf(Z)Ljava/lang/String;
			invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
			aload_3
			getfield frame_1/sl Lframe_0;
			astore_3
			getstatic java/lang/System/out Ljava/io/PrintStream;
			
			aload_3

			getfield frame_0/v0 Z

			invokestatic java/lang/String/valueOf(Z)Ljava/lang/String;
			invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
			aload_3
			getfield frame_0/sl Ljava/lang/Object;
			astore_3
       ; END 

       return

.end method
