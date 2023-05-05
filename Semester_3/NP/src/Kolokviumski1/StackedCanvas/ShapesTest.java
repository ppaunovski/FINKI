package Kolokviumski1.StackedCanvas;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

enum Color {
    RED, GREEN, BLUE
}
abstract class Shape implements Scalable, Stackable, Comparable<Shape>{
    String id;
    Color color;
    String type;

    public Shape(String id, Color color) {
        this.id = id;
        this.color = color;
    }

    abstract public String getType();

}

class Circle extends Shape{
    float radius;

    public Circle(String id, Color color, float radius) {
        super(id, color);
        this.radius = radius;
    }

    @Override
    public String getType() {
        return "Circle";
    }

    @Override
    public void scale(float scaleFactor) {
        this.radius = radius*scaleFactor;
    }

    @Override
    public float weight() {
        return (float) (Math.PI*radius*radius);
    }

    @Override
    public int compareTo(Shape o) {
        return Float.compare(this.weight(), o.weight());
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                '}';
    }
}

class Rectangle extends Shape{
    float width;
    float height;

    public Rectangle(String id, Color color, float width, float height) {
        super(id, color);
        this.width = width;
        this.height = height;
    }

    @Override
    public String getType() {
        return "Rectangle";
    }

    @Override
    public void scale(float scaleFactor) {
        this.width = width*scaleFactor;
        this.height = height*scaleFactor;
    }

    @Override
    public float weight() {
        return width*height;
    }

    @Override
    public int compareTo(Shape o) {
        return Float.compare(this.weight(), o.weight());
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}

class Canvas {
    List<Shape> shapes;

    public Canvas() {
        shapes = new LinkedList<>();
    }

    public void add(String id, Color color, float radius){
        Circle circle = new Circle(id, color, radius);

        if(shapes.size() == 0){
            shapes.add(circle);
//            System.out.println("NULA");
        }
        else {
            Shape []arr = new Shape[shapes.size()+1];
            for(int i=0; i<shapes.size(); i++){
                arr[i] = shapes.get(i);
            }
//            System.out.println(Arrays.toString(arr));
            insert(circle, arr);
            shapes = Arrays.stream(arr).collect(Collectors.toList());
        }
    }

    private void insert(Shape shape, Shape[] arr) {
//        System.out.println("INSERT----------");
//        System.out.println(Arrays.toString(arr));
        for(int i=1; i<arr.length; i++){
            if(arr[i] == null){
//                System.out.println("here?");
                if(arr[i-1].compareTo(shape) < 0){
                    Shape tmp = arr[i-1];
                    arr[i-1] = shape;
                    shape = tmp;
                    arr[i] = shape;
                }
                else {
                    arr[i] = shape;
                }
                return;
            }else if (arr[i].compareTo(shape) < 0){
//                System.out.println("or here?");
                shiftArray(arr, i-1, arr.length);
//                System.out.println(Arrays.toString(arr));
                arr[i-1] = shape;
                return;
            }
        }
//        System.out.println("AFTER INSERT-------");
//        System.out.println(Arrays.toString(arr));
    }

    private void shiftArray(Shape[] arr, int i, int length) {
        for(int j=length-1; j > i; j--){
            arr[j] = arr[j-1];
        }
    }

    public void add(String id, Color color, float width, float height){
        Rectangle rectangle = new Rectangle(id, color, width, height);
        if(shapes.size() == 0){
            shapes.add(rectangle);
        }
        else {
            Shape []arr = new Shape[shapes.size()+1];
            for(int i=0; i<shapes.size(); i++){
                arr[i] = shapes.get(i);
            }
            insert(rectangle, arr);
            shapes = Arrays.stream(arr).collect(Collectors.toList());
        }
    }

    public void scale(String id, float scaleFactor){
        shapes.stream()
                .filter(shape -> shape.id.equals(id))
                .forEach(shape -> shape.scale(scaleFactor));
    }

    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder();
        for(Shape shape : shapes){
            if(shape.getType().equals("Circle")){
                returnString.append(String.format("C: %-5s %-10s %10.2f\n", shape.id, shape.color, shape.weight()));
            }
            else if(shape.getType().equals("Rectangle")){
                returnString.append(String.format("R: %-5s %-10s %10.2f\n", shape.id, shape.color, shape.weight()));
            }
        }
        return returnString.toString();
    }
}

interface Scalable{
    void scale(float scaleFactor);
}

interface Stackable{
    float weight();
}

public class ShapesTest {
    public static void main(String[] args) {
        Canvas canvas = new Canvas();
        canvas.add("k2", Color.RED, 3);
        canvas.add("k4", Color.RED, 5);
        canvas.add("k3", Color.RED, 4);
        canvas.add("k1", Color.RED, 2);
        canvas.add("p3", Color.RED, 5, 5);
        canvas.add("p2", Color.RED, 2, 5);
        canvas.add("p1", Color.RED, 1, 5);
        canvas.add("p4", Color.RED, 10, 5);

        System.out.println(canvas);
    }
}

