package Kolokviumski1.generic_equation;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Line {
    Double coeficient;
    Double x;
    Double intercept;

    public Line(Double coeficient, Double x, Double intercept) {
        this.coeficient = coeficient;
        this.x = x;
        this.intercept = intercept;
    }

    public static Line createLine(String line) {
        String[] parts = line.split("\\s+");
        return new Line(
                Double.parseDouble(parts[0]),
                Double.parseDouble(parts[1]),
                Double.parseDouble(parts[2])
        );
    }

    public double calculateLine() {
        return coeficient * x + intercept;
    }

    @Override
    public String toString() {
        return String.format("%.2f * %.2f + %.2f", coeficient, x, intercept);
    }
}


public class EquationTester {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = Integer.parseInt(sc.nextLine());

        if (testCase == 1) { // Testing with Integer, Integer
            List<Equation<Integer, Integer>> equations1 = new ArrayList<>();
            List<Integer> inputs = new ArrayList<>();
            while (sc.hasNext()) {
                inputs.add(Integer.parseInt(sc.nextLine()));
            }

            // TODO: Add an equation where you get the 3rd integer from the inputs list, and the result is the sum of that number and the number 1000.
            Equation<Integer, Integer> equation1 = new Equation<>(() -> inputs.get(2), integer -> integer + 1000);



            // TODO: Add an equation where you get the 4th integer from the inputs list, and the result is the maximum of that number and the number 100.
            Equation<Integer, Integer> equation2 = new Equation<>(() -> inputs.get(3), integer -> Integer.max(integer, 100));

            EquationProcessor.process(inputs, List.of(equation1, equation2));


        } else { // Testing with Line, Integer
            List<Equation<Line, Double>> equations2 = new ArrayList<>();
            List<Line> inputs = new ArrayList<>();
            while (sc.hasNext()) {
                inputs.add(Line.createLine(sc.nextLine()));
            }

            //TODO Add an equation where you get the 2nd line, and the result is the value of y in the line equation.
            Equation<Line, Double> equation3 = new Equation<>(() -> inputs.get(1), line -> line.calculateLine());


            //TODO Add an equation where you get the 1st line, and the result is the sum of all y values for all lines that have a greater y value than that equation.
            Equation<Line, Double> equation4 = new Equation<>(() -> inputs.get(0), line -> inputs.stream().filter(ln -> ln.calculateLine() > line.calculateLine()).mapToDouble(ln -> ln.calculateLine()).sum());

            EquationProcessor.process(inputs, List.of(equation3, equation4));

        }
    }
}