package lab3.Pizzeria;

import java.util.Objects;

public class PizzaItem implements Item {
    String type;

    public PizzaItem(String type) throws InvalidPizzaTypeException {
        if(!type.equals("Standard") && !type.equals("Pepperoni") && !type.equals("Vegetarian")) throw new InvalidPizzaTypeException();
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public int getPrice() {
        switch (type){
            case "Standard": return 10;
            case "Pepperoni": return 12;
            case "Vegetarian": return 8;
            default: return 0;
        }
    }

    @Override
    public String toString() {
        return "PizzaItem{" +
                "type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PizzaItem pizzaItem = (PizzaItem) o;
        return Objects.equals(type, pizzaItem.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}
