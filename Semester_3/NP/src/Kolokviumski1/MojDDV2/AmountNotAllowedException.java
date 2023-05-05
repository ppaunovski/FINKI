package Kolokviumski1.MojDDV2;

public class AmountNotAllowedException extends Exception {
    public AmountNotAllowedException(String message) {
        super(String.format("Receipt with amount %s is not allowed to be scanned", message));
    }
}
