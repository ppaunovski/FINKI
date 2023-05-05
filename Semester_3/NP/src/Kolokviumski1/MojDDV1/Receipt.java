package Kolokviumski1.MojDDV1;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class Receipt {
    String id;
    List<Item> items;
    int sum;

    public Receipt(String id, List<Item> items, int sum) {
        this.id = id;
        this.items = items;
        this.sum = sum;
    }

    public static Receipt createReceipt(String line) throws AmountNotAllowedException{
        String []parts = line.split("\\s+");
        List<Item> items = new ArrayList<>();
        int sum = 0;
        for(int i=1; i<parts.length; i+=2){
            TaxType tax;
            switch (parts[i+1]){
                case "A":
                    tax = TaxType.TYPE_A;
                    break;
                case "B":
                    tax = TaxType.TYPE_B;
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

    public DoubleSummaryStatistics statistics(){
        return items.stream()
                .collect(Collectors.summarizingDouble(item -> item.getTax()*0.15));
    }

    @Override
    public String toString() {
        return id + " " + sum + " " + taxReturn();
    }

    public String taxReturn() {
        double taxReturn = 0.0;
        for(int i=0; i<items.size(); i++){
            taxReturn += items.get(i).getTax();
        }
        return String.format("%.2f", taxReturn*0.15);
    }
}
