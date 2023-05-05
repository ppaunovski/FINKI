package Kolokviumski1.GenericEvaluator;

public interface IEvaluator <T extends Comparable<T>>{
    boolean evaluate (T a, T b);
}
