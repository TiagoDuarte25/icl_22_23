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
			new ref_of_ref_of_bool
			dup
			invokespecial ref_of_ref_of_bool/<init>()V
			dup
			new ref_of_bool
			dup
			invokespecial ref_of_bool/<init>()V
			dup
			iconst_1
			putfield ref_of_bool/v Z
			putfield ref_of_ref_of_bool/v Lref_of_bool;
			putfield frame_0/v0 Lref_of_ref_of_bool;
			aload_3
			iconst_1
			putfield frame_0/v1 Z
			getstatic java/lang/System/out Ljava/io/PrintStream;
			iconst_1
			
			aload_3

			getfield frame_0/v0 Lref_of_ref_of_bool;

			getfield ref_of_ref_of_bool/v Lref_of_bool;
			getfield ref_of_bool/v Z
			
			aload_3

			getfield frame_0/v1 Z

			ior
			ineg
			iadd
			invokestatic java/lang/String/valueOf(Z)Ljava/lang/String;
			invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
			aload_3
			getfield frame_0/sl Ljava/lang/Object;
			astore_3
       ; END 

       return

.end method
