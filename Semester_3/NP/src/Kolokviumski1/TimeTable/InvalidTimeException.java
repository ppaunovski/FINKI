package Kolokviumski1.TimeTable;

public class InvalidTimeException extends Exception {
    public InvalidTimeException(String time) {
        super(time);
    }
}
