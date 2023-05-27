package Kolokviumski1.generic_equation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class EquationProcessor {
    public static <IN, OUT> void process(List<IN> input, List<Equation<IN, OUT>> equations){
        input.forEach(elem -> {
            System.out.println(elem);
            List<Optional<OUT>> outputs = new ArrayList<>();
            outputs = equations.stream().map(eq -> eq.calculate()).collect(Collectors.toList());

        });

    }
}
