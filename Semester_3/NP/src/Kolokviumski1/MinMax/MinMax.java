package Kolokviumski1.MinMax;

import java.util.Comparator;

public class MinMax<T extends Comparable<T>> {
    T min;
    T max;
    int duplicatesMAX;
    int duplicatesMIN;
     int UPDATED;
    public MinMax() {
        min = null;
        max = null;
    }

    public void update(T element){
        if(min == null || max == null){
            min = element;
            max = element;
            UPDATED = 1;
            duplicatesMAX = 1;
            duplicatesMIN = 1;
            return;
        }

        if(max.compareTo(element) == 0){
            duplicatesMAX++;
        }else if(min.compareTo(element) == 0){
            duplicatesMIN++;
        }

        if(min.compareTo(element) > 0){
//            if(!min.equals(max)){
//                UPDATED++;
//            }
            if(min.compareTo(max) != 0){
                UPDATED += duplicatesMIN;
                duplicatesMIN = 0;
            }
            min = element;
        }

        if(max.compareTo(element) < 0){
//            if(!min.equals(max)){
//                UPDATED++;
//            }
            if(max.compareTo(min) != 0){
                UPDATED += duplicatesMAX;
                duplicatesMAX = 0;
            }
            max = element;
        }
    }

    public T max(){
        return max;
    }

    public T min(){
        return min;
    }

    @Override
    public String toString() {
        return min.toString() + " " + max.toString() + " " + UPDATED + "\n";
    }
}
