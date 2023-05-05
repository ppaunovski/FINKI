package Kolokviumski1.MojDDV1;

public class Item {
    Integer price;
    TaxType taxType;

    public Item(Integer price, TaxType taxType) {
        this.price = price;
        this.taxType = taxType;
    }

    public double getTax() {
        switch (taxType){
            case TYPE_A: return price*0.18;
            case TYPE_B: return price*0.05;
            default: return 0;
        }
    }
}
