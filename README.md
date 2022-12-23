# Projeto ICL 
## 57677/58125

In this project we created a new programming language for the ICL course

## Guide

### Change classpath

There are some changes need to be done in order to run the bat scripts:
#### Interpreter

```
java -cp "your javacc.jar classpath" javacc Parser0.jj
javac *.java
java -cp "C:\Users\tduar\Desktop\Aulas\ICL\icl_22_23\src\compiled_classes" ICLInterpreter
```

#### Compiler

```
java -cp "your javacc.jar classpat" javacc Parser0.jj
javac -d compiled_classes *.java
java -cp "your_src\compiled_classes"; ICLCompiler source.icl
java -jar "your_jasmin_classpath\jasmin.jar" *.j
java Header
```

In order to test the project, you can run the input tests in [tests.txt](/src/tests.txt)