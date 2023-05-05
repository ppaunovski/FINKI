package lab5.GenericScheduler;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Scheduler<T> {
    private List<Timestamp<T>> timestamps;

    public Scheduler() {
        this.timestamps = new ArrayList<>();
    }

    public void add(Timestamp<T> t){
        timestamps.add(t);
    }

    public boolean remove(Timestamp<T> t){
        return timestamps.remove(t);
    }

    public Timestamp<T> next(){
        LocalDateTime now = LocalDateTime.now();
        //System.out.println(now.toString());
        Optional<Timestamp<T>> next = timestamps.stream()
                .filter(t -> t.compareTo(new Timestamp<>(now, null)) > 0)
                .min(Timestamp::compareTo);
        return next.orElse(new Timestamp<>(null, null));
    }

    public Timestamp<T> last(){
        LocalDateTime now = LocalDateTime.now();
        Optional<Timestamp<T>> last = timestamps.stream()
                .filter(t -> t.compareTo(new Timestamp<>(now, null)) < 0)
                .max(Timestamp::compareTo);
        return last.orElse(new Timestamp<>(null, null));
    }

    public List<Timestamp<T>> getAll(LocalDateTime begin, LocalDateTime end){
        return timestamps.stream()
                .filter(t -> t.compareTo(new Timestamp<>(begin, null)) > 0 && t.compareTo(new Timestamp<>(end, null)) < 0)
                .filter(t -> t.compareTo(new Timestamp<>(begin, null)) != 0 || t.compareTo(new Timestamp<>(end, null)) != 0)
                .collect(Collectors.toList());
    }
}

class ScheduleTest{
    public static void main(String[] args) {
        Scheduler<String> scheduler = new Scheduler<>();
        LocalDateTime now = LocalDateTime.now();
        Timestamp<String> timestamp1 = new Timestamp<>(now.plusMonths(1), "plus 1 month");
        Timestamp<String> timestamp2 = new Timestamp<>(now.plusHours(1), "plus 1 hour");
        Timestamp<String> timestamp3 = new Timestamp<>(now.minusMonths(12), "minus 12 months");
        scheduler.add(timestamp1);
        scheduler.add(timestamp2);
        scheduler.add(timestamp3);
        System.out.println(scheduler.next());
        System.out.println(scheduler.last());
        System.out.println(scheduler.getAll(LocalDateTime.now(), LocalDateTime.now().plusHours(1)));
    }
}
