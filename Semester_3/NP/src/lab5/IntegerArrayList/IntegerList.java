package lab5.IntegerArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntegerList {
    private List<Integer> list;

    public IntegerList() {
        list  = new ArrayList<>();
    }

    public IntegerList(Integer []numbers){
        list = new ArrayList<>();
        list = Arrays.stream(numbers).toList();
    }

}
