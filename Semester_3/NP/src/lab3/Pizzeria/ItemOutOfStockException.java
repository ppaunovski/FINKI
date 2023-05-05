package lab3.Pizzeria;

public class ItemOutOfStockException extends Exception{
    public ItemOutOfStockException(Item item) {
        super("ItemOutOfStockException called with " + item.toString());
    }
}
