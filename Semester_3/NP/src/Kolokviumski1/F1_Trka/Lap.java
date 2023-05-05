package Kolokviumski1.F1_Trka;

import java.time.Duration;
import java.time.LocalDateTime;

public class Lap implements Comparable{
    private int minutes, seconds, milliseconds;

    public Lap(int minutes, int seconds, int milliseconds) {
        this.minutes = minutes;
        this.seconds = seconds;
        this.milliseconds = milliseconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getMilliseconds() {
        return milliseconds;
    }

    public int totalTimeInMs(){
        return minutes*60*1000 + seconds*1000 + milliseconds;
    }

    @Override
    public int compareTo(Object o) {
        Lap other = (Lap) o;
        return  this.totalTimeInMs() - other.totalTimeInMs();
    }

    @Override
    public String toString() {
//        return Duration.ofMillis(minutes*60*1000 + seconds*1000 + milliseconds).toString();
        return String.format("%d:%02d:%03d", minutes, seconds, milliseconds);
    }
}
