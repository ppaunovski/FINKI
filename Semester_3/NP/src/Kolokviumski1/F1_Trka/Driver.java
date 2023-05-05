package Kolokviumski1.F1_Trka;

import java.util.*;

public class Driver implements Comparable{
    String name;
    List<Lap> laps;

    public Driver(String name, List<Lap> laps) {
        this.name = name;
        this.laps = laps;
    }

    public Driver(String line){
        String[] parts = line.split(" ");
        this.name = parts[0];
        this.laps = new ArrayList<Lap>();
        for(int i=1; i<parts.length; i++){
            String []p = parts[i].split(":");
            laps.add(new Lap(Integer.parseInt(p[0]), Integer.parseInt(p[1]), Integer.parseInt(p[2])));

        }
    }

    public String getName() {
        return name;
    }

    public List<Lap> getLaps() {
        return laps;
    }

    public Lap getBestLap(){
        Optional<Lap> lap =  laps.stream()
                .min(Lap::compareTo);
        return lap.orElse(null);
    }

    @Override
    public String toString() {
        return String.format("%-12s%s", name, getBestLap().toString());
    }

    @Override
    public int compareTo(Object o) {
        Driver other = (Driver) o;
        return this.getBestLap().totalTimeInMs() - other.getBestLap().totalTimeInMs();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return Objects.equals(name, driver.name) && Objects.equals(laps, driver.laps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, laps);
    }
}
