package lab6.SuperString;

public class OrderedString implements Comparable<OrderedString> {
    String string;
    Integer order;

    public OrderedString(String string, Integer order) {
        this.string = string;
        this.order = order;
    }

    @Override
    public int compareTo(OrderedString o) {
        return Integer.compare(this.order,o.order);
    }
}
