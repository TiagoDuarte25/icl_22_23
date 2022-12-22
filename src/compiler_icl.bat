java -cp "C:\Users\tduar\Desktop\Aulas\ICL\javacc-javacc-7.0.10\target\javacc.jar" javacc Parser0.jj
javac -d compiled_classes *.java
java -cp "C:\Users\tduar\Desktop\Aulas\ICL\icl_22_23\src\compiled_classes"; ICLCompiler source.icl
java -jar "C:\Users\tduar\Desktop\Aulas\Other_Folders\jasmin-2.4\jasmin.jar" *.j
java Header