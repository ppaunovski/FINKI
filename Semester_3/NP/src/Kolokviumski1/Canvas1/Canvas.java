package Kolokviumski1.Canvas1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class Canvas implements Comparable<Canvas> {
    String canvasId;
    List<Integer> sizes;

    public Canvas(String canvasId, List<Integer> sizes) {
        this.canvasId = canvasId;
        this.sizes = sizes;
    }

    public static Canvas createCanvas(String line) {
        String []parts = line.split("\\s+");
        List<Integer> list = new ArrayList<>();
        for(int i=1; i<parts.length; i++){
            list.add(Integer.parseInt(parts[i]));
        }
        return new Canvas(parts[0], list);
    }

    public int countSquares(){
        return sizes.size();
    }

    public String getCanvasId() {
        return canvasId;
    }

    public List<Integer> getSizes() {
        return sizes;
    }

    @Override
    public int compareTo(Canvas o) {
        return Integer.compare(this.perimeter(), o.perimeter());
    }

    private int perimeter() {
        IntSummaryStatistics intSummaryStatistics = sizes.stream()
                .collect(Collectors.summarizingInt(side -> side*4));
        return (int)intSummaryStatistics.getSum();
    }

    @Override
    public String toString() {
        return canvasId + " " + sizes.size() + " " + perimeter();
    }
}
