package Kolokviumski1.Canvas2;

public class Square implements Shape{
    double side;

    public Square(double side) {
        this.side = side;
    }

    @Override
    public double area() {
        return Math.pow(side, 2);
    }

    @Override
    public String getType() {
        return "S";
    }
}
