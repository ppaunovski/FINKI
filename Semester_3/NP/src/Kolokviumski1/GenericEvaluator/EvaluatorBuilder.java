package Kolokviumski1.GenericEvaluator;

public class EvaluatorBuilder {
    static IEvaluator build (String operator){

        switch (operator){
            case ">":
                return ((a, b) -> a.compareTo(b) > 0);
            case "==":
                return ((a, b) -> a.compareTo(b) == 0);
            case "!=":
                return (((a, b) -> a.compareTo(b) != 0));
            case "<":
                return (((a, b) -> a.compareTo(b) < 0));
            default:
                return null;
        }
    }
}

class Evaluator {
    static <T extends Comparable<T>>boolean evaluateExpression (T left, T right, String operator){
        return EvaluatorBuilder.build(operator).evaluate(left, right);
    }
}
