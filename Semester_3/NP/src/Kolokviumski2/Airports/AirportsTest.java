package Kolokviumski2.Airports;

import java.util.*;
import java.util.stream.Collectors;

class Flight{
    String from;
    String to;
    int time;
    int duration;

    public Flight(String from, String to, int time, int duration) {
        this.from = from;
        this.to = to;
        this.time = time;
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return time == flight.time && duration == flight.duration && Objects.equals(from, flight.from) && Objects.equals(to, flight.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, time, duration);
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public int getTime() {
        return time;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return String.format("%s-%s %s-%s %s", from, to, startTime(), endTime(), durationString());
    }

    private String  durationString() {
        Integer hour = (duration)/60;
        Integer minutes = (duration)%60;
        return String.format("%dh%02dm", hour, minutes);
    }

    private String endTime() {
        Integer hour = (time+duration)/60%24;
        Integer minutes = (time+duration)%60;
        Integer days = (time+duration)/60/24;
        String ret = String.format("%02d:%02d", hour, minutes);
        if(days > 0){
            ret = String.format("%02d:%02d +%dd", hour, minutes, days);
        }
        return ret;
    }

    private String startTime() {
        Integer hour = time/60;
        Integer minutes = time%60;
        return String.format("%02d:%02d", hour, minutes);
    }
}

class Airport{
    String name;
    String country;
    String code;
    int passengers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport = (Airport) o;
        return passengers == airport.passengers && Objects.equals(name, airport.name) && Objects.equals(country, airport.country) && Objects.equals(code, airport.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country, code, passengers);
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getCode() {
        return code;
    }

    public int getPassengers() {
        return passengers;
    }

    public Airport(String name, String country, String code, int passengers) {
        this.name = name;
        this.country = country;
        this.code = code;
        this.passengers = passengers;
    }
}

class Airports{

    Map<Airport, TreeSet<Flight>> flightsByAirport;
    Map<String, Airport> airportByCode;
    Map<Airport, TreeSet<Flight>> flightsByDestination;

    public Airports() {
        flightsByAirport = new HashMap<>();
        airportByCode = new HashMap<>();
        flightsByDestination = new HashMap<>();
    }

    public void addAirport(String name, String country, String code, int passengers){
        Airport airport = new Airport(name, country, code, passengers);
        airportByCode.putIfAbsent(code, airport);
        flightsByAirport.putIfAbsent(airport, new TreeSet<>(Comparator.comparing(Flight::getTo).thenComparing(Flight::getTime)));
        flightsByDestination.putIfAbsent(airport, new TreeSet<>(Comparator.comparing(Flight::getTo).thenComparing(Flight::getTime)));
    }

    public void addFlights(String from, String to, int time, int duration){
        Flight flight = new Flight(from, to, time, duration);
        Airport fromA = airportByCode.get(from);
        Airport toA = airportByCode.get(to);
        flightsByAirport.get(fromA).add(flight);
       // flightsByAirport.get(toA).add(flight);
        flightsByDestination.get(toA).add(flight);
    }

    public void showFlightsFromAirport(String code){

//        London Heathrow (LHR)
//                United Kingdom
//        70037417
//        1. LHR-ATL 12:44-17:35 4h51m
//        2. LHR-BKK 04:02-05:55 1h53m
//        3. LHR-CLT 16:24-20:18 3h54m
//        4. LHR-DFW 21:20-23:45 2h25m

        Airport airport = airportByCode.get(code);
        Set<Flight> flights = flightsByAirport.get(airport);
        System.out.println(airport.name + " (" + code + ")");
        System.out.println(airport.country);
        System.out.println(airport.passengers);

        Iterator<Flight> itr = flights.iterator();
        int i = 1;
        while (itr.hasNext()){
            Flight curr = itr.next();
            System.out.println(String.format("%d. %s", i, curr));
            i++;
        }

    }

    public void showDirectFlightsFromTo(String from, String to) {
        StringBuilder sb = new StringBuilder();

        flightsByAirport.get(airportByCode.get(from)).stream()
                .filter(flight -> flight.to.equals(to))
                .forEach(sb::append);

        if(sb.length() == 0){
            sb.append("No flights from ").append(from).append(" to ").append(to);
        }
        System.out.println(sb);
    }
    public void showDirectFlightsTo(String to){
        flightsByDestination.get(airportByCode.get(to)).stream()
                .forEach(System.out::println);
    }
}

public class AirportsTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Airports airports = new Airports();
        int n = scanner.nextInt();
        scanner.nextLine();
        String[] codes = new String[n];
        for (int i = 0; i < n; ++i) {
            String al = scanner.nextLine();
            String[] parts = al.split(";");
            airports.addAirport(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]));
            codes[i] = parts[2];
        }
        int nn = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < nn; ++i) {
            String fl = scanner.nextLine();
            String[] parts = fl.split(";");
            airports.addFlights(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
        }
        int f = scanner.nextInt();
        int t = scanner.nextInt();
        String from = codes[f];
        String to = codes[t];
        System.out.printf("===== FLIGHTS FROM %S =====\n", from);
        airports.showFlightsFromAirport(from);
        System.out.printf("===== DIRECT FLIGHTS FROM %S TO %S =====\n", from, to);
        airports.showDirectFlightsFromTo(from, to);
        t += 5;
        t = t % n;
        to = codes[t];
        System.out.printf("===== DIRECT FLIGHTS TO %S =====\n", to);
        airports.showDirectFlightsTo(to);
    }
}

// vashiot kod ovde

