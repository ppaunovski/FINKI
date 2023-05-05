package lab5.GenericQueue;

public class EmptyQueueException extends Exception{
    public EmptyQueueException() {
        super("The queue is empty!");
    }
}
