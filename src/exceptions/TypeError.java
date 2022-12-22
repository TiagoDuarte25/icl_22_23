package exceptions;
public class TypeError extends Exception {

    public TypeError(String op) {
        super("Illegal argument types to " + op + " operator");
    }

}
