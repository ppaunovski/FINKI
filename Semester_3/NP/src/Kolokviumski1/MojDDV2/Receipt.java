package Kolokviumski1.MojDDV2;

import Kolokviumski1.MojDDV1.AmountNotAllowedException;
import Kolokviumski1.MojDDV1.Item;
import Kolokviumski1.MojDDV1.TaxType;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
    String id;
    List<Kolokviumski1.MojDDV1.Item> items;
    int sum;

    public Receipt(String id, List<Kolokviumski1.MojDDV1.Item> items, int sum) {
        this.id = id;
        this.items = items;
        this.sum = sum;
    }

    public static Receipt createReceipt(String line) throws AmountNotAllowedException {
        String []parts = line.split("\\s+");
        List<Kolokviumski1.MojDDV1.Item> items = new ArrayList<>();
        int sum = 0;
        for(int i=1; i<parts.length; i+=2){
            Kolokviumski1.MojDDV1.TaxType tax;
            switch (parts[i+1]){
                case "A":
                    tax = Kolokviumski1.MojDDV1.TaxType.TYPE_A;
                    break;
                case "B":
                    tax = Kolokviumski1.MojDDV1.TaxType.TYPE_B;
                    break;
                default:
                    tax = TaxType.TYPE_V;
            }
            int price = Integer.parseInt(parts[i]);
            sum += price;
            items.add(new Item(price, tax));
        }
        if(sum > 30000){
            throw new AmountNotAllowedException(String.format("%d", sum));
        }
        return new Receipt(parts[0], items, sum);
    }

    @Override
    public String toString() {
        return id + " " + sum + " " + taxReturn();
    }

    private String taxReturn() {
        double taxReturn = 0.0;
        for(int i=0; i<items.size(); i++){
            taxReturn += items.get(i).getTax();
        }
        return String.format("%.2f", taxReturn*0.15);
    }
}
