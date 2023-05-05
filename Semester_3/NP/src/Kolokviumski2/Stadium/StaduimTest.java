package Kolokviumski2.Stadium;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class SeatTakenException extends Exception{
    public SeatTakenException() {
    }
}

class SeatNotAllowedException extends Exception{
    public SeatNotAllowedException() {
    }
}
class Sector{
    String name;
    String code;
    int seatCount;
    //info za zafatenost?
    int seatsTaken;
    int type;

    Map<Integer, Boolean> takenSeatsBySeatNumber;

    public Sector(String code, int seatCount) {
        this.code = code;
        this.seatCount = seatCount;
        seatsTaken = 0;
        takenSeatsBySeatNumber = new HashMap<>();
        type = -1;
    }

    public String getCode() {
        return code;
    }

    public int getSeatCount() {
        return seatCount;
    }
    public int freeSeats(){
        return seatCount - seatsTaken;
    }
    public int getSeatsTaken() {
        return seatsTaken;
    }

    public int getType() {
        return type;
    }

    public void buyTicket(int seat, int type) throws SeatTakenException, SeatNotAllowedException {
        if(this.type == -1){
            this.type = type;
            takenSeatsBySeatNumber.put(seat, true);
            seatsTaken++;
        }
        else if(this.type == type || type == 0) {
            if(takenSeatsBySeatNumber.containsKey(seat)){
                throw new SeatTakenException();
            }
            takenSeatsBySeatNumber.put(seat, true);
            seatsTaken++;
        } else {
            throw new SeatNotAllowedException();
        }
    }

    @Override
    public String toString() {
        return String.format("%s\t%d/%d\t%.1f%%", code, freeSeats(), seatCount, fullnessInPercentages());
    }

    private double fullnessInPercentages() {
        return 100 - (double)freeSeats() / seatCount * 100;
    }
}

class Stadium{
    String name;
    //site sektori?
    Map<String, Sector> sectorByName;


    public Stadium(String name) {
        this.name = name;
        sectorByName = new HashMap<>();
    }

    void createSectors(String[] sectorNames, int[] sizes){
        for(int i=0; i<sectorNames.length; i++){
            Sector sector = new Sector(sectorNames[i], sizes[i]);
            sectorByName.putIfAbsent(sectorNames[i], sector);
        }
    }

    void buyTicket(String sectorName, int seat, int type) throws SeatTakenException, SeatNotAllowedException {
        sectorByName.get(sectorName).buyTicket(seat, type);
    }

    void showSectors(){
        sectorByName.values().stream()
                .sorted(Comparator.comparing(Sector::freeSeats).reversed().thenComparing(Sector::getCode))
                .forEach(System.out::println);
    }
}

public class StaduimTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        String[] sectorNames = new String[n];
        int[] sectorSizes = new int[n];
        String name = scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            sectorNames[i] = parts[0];
            sectorSizes[i] = Integer.parseInt(parts[1]);
        }
        Stadium stadium = new Stadium(name);
        stadium.createSectors(sectorNames, sectorSizes);
        n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            try {
                stadium.buyTicket(parts[0], Integer.parseInt(parts[1]),
                        Integer.parseInt(parts[2]));
            } catch (SeatNotAllowedException e) {
                System.out.println("SeatNotAllowedException");
            } catch (SeatTakenException e) {
                System.out.println("SeatTakenException");
            }
        }
        stadium.showSectors();
    }
}