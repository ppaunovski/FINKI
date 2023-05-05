package lab3.Pizzeria;

public class ArrayIndexOutOfBоundsException extends Exception{
    public ArrayIndexOutOfBоundsException(int idx) {
        super("ArrayIndexOutOfBоundsException called with " + idx);
    }
}
