javacc -LOOKAHEAD:2 Parser0.jj
javac -d compiled_classes *.java
java -cp "C:\Users\ticog\OneDrive\Ambiente de Trabalho\Faculdade\4ºAno\1º Semestre\ICL\icl_22_23\src\compiled_classes"; ICLCompiler source.icl
java -jar "C:\Users\ticog\OneDrive\Ambiente de Trabalho\Faculdade\4ºAno\1º Semestre\ICL\jasmin-2.4\jasmin.jar" *.j
java Header
