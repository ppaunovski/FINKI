package lab3.Pizzeria;

import java.util.Objects;

public class ExtraItem implements Item {
    String type;

    public ExtraItem(String type) throws InvalidExtraTypeException {
        if(!type.equals("Coke") && !type.equals("Ketchup")) throw new InvalidExtraTypeException();
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public int getPrice() {
        switch (type){
            case "Coke": return 5;
            case "Ketchup": return 3;
            default: return 0;
        }
    }

    @Override
    public String toString() {
        return "ExtraItem{" +
                "type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExtraItem extraItem = (ExtraItem) o;
        return Objects.equals(type, extraItem.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}
