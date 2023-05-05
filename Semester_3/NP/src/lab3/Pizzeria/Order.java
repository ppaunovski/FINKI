package lab3.Pizzeria;

import java.util.*;

public class Order {
    Item[] itemsInOrder;
    int[] numberOfItems;
    boolean isLocked;

    public Order() {
        itemsInOrder = new Item[0];
        numberOfItems = new int[0];
        isLocked = false;
    }
    public void addItem(Item item, int count) throws ItemOutOfStockException, OrderLockedException{
        if(isLocked) throw new OrderLockedException();
        if(count > 10)  throw new ItemOutOfStockException(item);
        boolean isPresent = false;

        for(int i=0; i<itemsInOrder.length; i++){
            if(itemsInOrder[i].equals(item)){
                numberOfItems[i] = count;
                isPresent = true;
            }
        }

        if(!isPresent){
            itemsInOrder = Arrays.copyOf(itemsInOrder, itemsInOrder.length + 1);
            itemsInOrder[itemsInOrder.length - 1] = item;
            numberOfItems = Arrays.copyOf(numberOfItems, numberOfItems.length + 1);
            numberOfItems[numberOfItems.length - 1] = count;
        }
    }

    public int getPrice(){
        int sum = 0;
        for(int i=0; i< itemsInOrder.length; i++){
            sum += (itemsInOrder[i].getPrice() * numberOfItems[i]);
        }
        return sum;
    }

    public void displayOrder(){
        for(int i=0; i<itemsInOrder.length; i++) {
            System.out.println("  " + (i + 1) + ".%-10s" + numberOfItems[i] + "\t" +  (itemsInOrder[i].getPrice() * numberOfItems[i]) + "$");
            System.out.printf("%3d.%-15sx%2d%5d$\n", i+1, itemsInOrder[i].getType(), numberOfItems[i], itemsInOrder[i].getPrice() * numberOfItems[i]);
        }
        System.out.printf("%-22s%5d$\n", "Total:", getPrice());
    }

    public void removeItem(int idx) throws ArrayIndexOutOfBоundsException, OrderLockedException{
        if(isLocked) throw new OrderLockedException();
        if(idx >= itemsInOrder.length)  throw new ArrayIndexOutOfBоundsException(idx);
        Item []tmpArray = new Item[itemsInOrder.length-1];
        int []tmpArray2 = new int[numberOfItems.length-1];
        int index = 0;
        for(int i=0; i<itemsInOrder.length; i++){
            if(i != idx){
//                itemsInOrder[i] = itemsInOrder[i+1];
//                numberOfItems[i] = numberOfItems[i+1];
//                itemsInOrder = Arrays.copyOf(itemsInOrder, itemsInOrder.length - 1);
//                numberOfItems = Arrays.copyOf(numberOfItems, numberOfItems.length - 1);
                tmpArray[index] = itemsInOrder[i];
                tmpArray2[index++] = numberOfItems[i];
            }
        }
        itemsInOrder = Arrays.copyOf(tmpArray, tmpArray.length);
        numberOfItems = Arrays.copyOf(tmpArray2, tmpArray2.length);
    }

    public void lock() throws EmptyOrder{
        if(itemsInOrder.length == 0) throw new EmptyOrder();
        isLocked = true;
    }
}
