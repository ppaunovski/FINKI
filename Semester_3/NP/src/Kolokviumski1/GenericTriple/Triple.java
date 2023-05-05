package Kolokviumski1.GenericTriple;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class Triple<T extends Number> {
    List<T> list;

    public Triple(T a, T b, T c) {
        list = new ArrayList<>();
        list.add(a);
        list.add(b);
        list.add(c);
    }

    private DoubleSummaryStatistics statistics(){
        return list.stream()
                .collect(Collectors.summarizingDouble(Number::doubleValue));
    }

    public double max(){
        return statistics().getMax();
    }

    public double avarage(){
        return statistics().getAverage();
    }

    public void sort(){
        list = list.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return String.format("%.2f %.2f %.2f", list.get(0).doubleValue(), list.get(1).doubleValue(), list.get(2).doubleValue());
    }
}
