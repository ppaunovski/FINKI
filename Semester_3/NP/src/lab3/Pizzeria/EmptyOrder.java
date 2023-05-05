package lab3.Pizzeria;

public class EmptyOrder extends Exception{
    public EmptyOrder() {
        super("EmptyOrderException called");
    }
}
