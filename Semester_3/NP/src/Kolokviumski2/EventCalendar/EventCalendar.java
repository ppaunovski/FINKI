package Kolokviumski2.EventCalendar;

import java.time.LocalDate;
import java.time.temporal.TemporalAccessor;
import java.util.*;

class Event{
    private String name;
    private String location;
    private Date date;

    public Event(String name, String location, Date date) {
        this.name = name;
        this.location = location;
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(name, event.name) && Objects.equals(location, event.location) && Objects.equals(date, event.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, location, date);
    }

    @Override
    public String toString() {
        return String.format("dd MMM, YYY HH:mm at %s, %s", date, location, name);
    }
}

class WrongDateException extends Exception{
    public WrongDateException(String message) {
        super(message);
    }
}

public class EventCalendar {
    private Integer yearOfCalendar;
    private Map<Integer, List<Event>> eventsByDate;
    private Map<Integer, List<Event>> eventsByMonth;

    public EventCalendar(int year) {
        yearOfCalendar = year;
        this.eventsByDate = new HashMap<>();
        this.eventsByMonth = new TreeMap<>();
    }

    public void addEvent(String name, String location, Date date) throws WrongDateException {
        if(date.getYear() != yearOfCalendar){
            System.out.println(date.getMonth());
            throw new WrongDateException("Wrong date: " + date.toString());
        }
        Event newEvent = new Event(name, location, date);
        eventsByDate.putIfAbsent(date.getDate(), new ArrayList<>());
        eventsByDate.get(date.getDate()).add(newEvent);

        eventsByMonth.putIfAbsent(date.getMonth(), new ArrayList<>());
        eventsByMonth.get(date.getMonth()).add(newEvent);
    }

    public void listEvents(Date date){
        eventsByDate.get(date.getDate()).forEach(System.out::println);
    }

    public void listByMonth() {
        eventsByMonth.entrySet().stream().map(set -> String.format("%d : %d", set.getKey(), set.getValue().size())).forEach(System.out::println);
    }
}
