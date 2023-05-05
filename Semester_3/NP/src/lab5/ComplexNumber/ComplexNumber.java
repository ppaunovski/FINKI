package lab5.ComplexNumber;

import java.util.Comparator;

public class ComplexNumber<T extends Number, U extends Number> implements Comparable<ComplexNumber<?,?>> {
    private T real;
    private U imaginary;

    public ComplexNumber(T real, U imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public T getReal() {
        return real;
    }

    public double modul(){
        return Math.sqrt(Math.pow(real.doubleValue(), 2) + Math.pow(imaginary.doubleValue(), 2));
    }

    public U getImaginary() {
        return imaginary;
    }

    @Override
    public int compareTo(ComplexNumber<?, ?> o) {
        return Double.compare(this.modul(), o.modul());
    }

    @Override
    public String toString() {
        return String.format("%.2f%s%.2fi", this.real.doubleValue(), this.imaginary.doubleValue() < 0 ? "" : "+" , this.imaginary.doubleValue());
    }
}
