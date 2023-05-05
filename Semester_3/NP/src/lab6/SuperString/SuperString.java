package lab6.SuperString;

import java.util.LinkedList;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SuperString {
    LinkedList<OrderedString> list;
    static Integer order = 0;
    public SuperString() {
        list = new LinkedList<>();
    }

    public void append(String s){
        list.addLast(new OrderedString(s, order++));
    }

    public void insert(String s){
        list.addFirst(new OrderedString(s, order++));
    }

    public boolean contains(String str){
        StringBuilder stringBuilder = new StringBuilder();
        list.forEach(s -> stringBuilder.append(s.string));
        return stringBuilder.toString().contains(str);
    }

    private static OrderedString reverseString(OrderedString s){
        String retStr = "";
        for(int i=s.string.length()-1; i>=0; i--){
            retStr += Character.toString(s.string.charAt(i));
        }
        return new OrderedString(retStr, s.order);
    }

    public  void reverse(){
        LinkedList<OrderedString> newList = new LinkedList<>();
        list.forEach(newList::addFirst);
        LinkedList<OrderedString> newnewList = new LinkedList<>();
        newList.stream().map(SuperString::reverseString).forEach(newnewList::addLast);
        list = newnewList;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        list.forEach(s -> str.append(s.string));
        return str.toString();
    }

    public void removeLast(int k){
        OrderedString highestOrderString = list.stream()
                .max(Comparator.naturalOrder())
                .orElse(null);
        int highestOrder = 0;
        if(highestOrderString != null){
            highestOrder = highestOrderString.order;
        }

        int finalHighestOrder = highestOrder;
        LinkedList<OrderedString> newList = new LinkedList<>();
        list.stream()
                .filter(s -> s.order < finalHighestOrder - k )
                .forEach(s -> newList.addLast(s));
        list = newList;
    }
}
