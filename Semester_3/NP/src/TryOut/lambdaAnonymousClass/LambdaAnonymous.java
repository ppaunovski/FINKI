package TryOut.lambdaAnonymousClass;

import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class LambdaAnonymous {
    public static void main(String[] args) {
        Supplier supplier = new Supplier() {
            @Override
            public Object get() {
                return Math.floor(Math.random()*100);
            }
        };

        Consumer consumer = new Consumer() {
            @Override
            public void accept(Object o) {
                System.out.println(o.toString());
            }
        };

        Function<Integer, Integer> function = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer*5;
            }
        };

        BiFunction<Integer, Integer, String> biFunction = new BiFunction<Integer, Integer, String>() {
            @Override
            public String apply(Integer integer, Integer integer2) {
                return String.format("%d", integer+integer2);
            }
        };

        System.out.println("SUPPLIER");
        System.out.println(supplier.get());

        System.out.println("CONSUMER");
        consumer.accept("Consumer Anonymous");

        System.out.println("FUNCTION");
        System.out.println(function.apply(5));

        System.out.println("BI FUNCTION");
        System.out.println(biFunction.apply(5, 13));
    }
}
