package Kolokviumski1.Canvas2;

public class Circle implements Shape{
    double radius;

    public Circle(double radius) {
        this.radius = radius;
    }


    @Override
    public double area() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public String getType() {
        return "C";
    }
}
