package Kolokviumski1.GenericFraction;

public class GenericFraction<T extends Number, U extends Number> {
    T numerator;
    U denominator;

    public GenericFraction(T numerator, U denominator) throws ZeroDenominatorException {
        if(denominator.equals(0)) throw new ZeroDenominatorException();
        this.numerator = numerator;
        this.denominator = denominator;
    }
    public GenericFraction<Double, Double> add(GenericFraction<? extends Number, ? extends Number> gf) throws ZeroDenominatorException {
        return new GenericFraction<Double, Double>( gf.denominator.doubleValue() * this.numerator.doubleValue() + gf.numerator.doubleValue()* this.denominator.doubleValue(),
                 this.denominator.doubleValue() *  gf.denominator.doubleValue());
    }

    public double toDouble(){
        return this.numerator.doubleValue() / this.denominator.doubleValue();
    }

    @Override
    public String toString() {
        Integer nzd = NZD(this.denominator.intValue(), this.numerator.intValue());
        return String.format("%.2f / %.2f", this.numerator.doubleValue()/nzd, this.denominator.doubleValue()/nzd);
    }

    private Integer NZD(int denominator, int numerator) {
        if(numerator == 0)
            return denominator;
        if(numerator == 1)
            return 1;

        return NZD(numerator, denominator % numerator);
    }
}
