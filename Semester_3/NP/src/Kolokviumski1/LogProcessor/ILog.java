package Kolokviumski1.LogProcessor;

public interface ILog {
    //TODO write methods definitions here;
    String getType();
    String getMessage();
    void setMessage(String newMessage);
    long getTimestamp();
}