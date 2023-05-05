package Kolokviumski1.Canvas2;

public class IrregularCanvasException extends Exception{
    public IrregularCanvasException(String message, double maxArea) {
        super(String.format("Canvas %s has a shape with area larger than %.2f", message, maxArea));
    }
}
