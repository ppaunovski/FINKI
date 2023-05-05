package Kolokviumski1.Canvas2;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class Canvas implements Comparable<Canvas> {
    List<Shape> shapes;
    String canvasId;

    public Canvas(List<Shape> shapes, String canvasId) {
        this.shapes = shapes;
        this.canvasId = canvasId;
    }

    public static Canvas createCanvas(String line, double maxArea) throws IrregularCanvasException {
        String []parts = line.split("\\s+");
        List<Shape> sps = new ArrayList<>();
        for(int i=1; i<parts.length; i+=2){
            if(parts[i].equals("S")){
                Square sq = new Square(Double.parseDouble(parts[i+1]));
                if(sq.area() < maxArea){
                    sps.add(sq);
                }else throw new IrregularCanvasException(parts[0], maxArea);
            }
            else if(parts[i].equals("C")){
                Circle circle = new Circle(Double.parseDouble(parts[i+1]));
                if(circle.area() < maxArea){
                    sps.add(circle);
                }else throw new IrregularCanvasException(parts[0], maxArea);
            }
        }
        return new Canvas(sps, parts[0]);
    }


    @Override
    public int compareTo(Canvas o) {
        return Double.compare(this.stats().getSum(), o.stats().getSum());
    }

    private DoubleSummaryStatistics stats() {
        return shapes.stream()
                .collect(Collectors.summarizingDouble(Shape::area));
    }

    @Override
    public String toString() {
        int circles = 0, squares = 0;
        for (Shape shape : shapes) {
            if (shape.getType().equals("S"))
                squares++;
            else circles++;
        }
        return String.format("%s %d %d %d %.2f %.2f %.2", canvasId, shapes.size(), circles, squares, stats().getMin(), stats().getMax(), stats().getAverage());
    }
}
