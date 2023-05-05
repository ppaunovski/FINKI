package Kolokviumski2.StopCoronaApp;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class UserIdAlreadyExistsException extends Exception{
    public UserIdAlreadyExistsException() {
        super("ERROR");
    }
}

interface ILocation{
    double getLongitude();

    double getLatitude();

    LocalDateTime getTimestamp();
}

//class Contact implements ILocation{
//    User user1;
//    User user2;
//    Double longitude;
//    Double latitude;
//    LocalDateTime timestamp;
//
//    public Contact(User user1, User user2, Double longitude, Double latitude, LocalDateTime timestamp) {
//        this.user1 = user1;
//        this.user2 = user2;
//        this.longitude = longitude;
//        this.latitude = latitude;
//        this.timestamp = timestamp;
//    }
//
//    public static Contact wereInContact(User u1, User u2){
//
//    }
//
//    @Override
//    public double getLongitude() {
//        return 0;
//    }
//
//    @Override
//    public double getLatitude() {
//        return 0;
//    }
//
//    @Override
//    public LocalDateTime getTimestamp() {
//        return null;
//    }
//}

class User implements Comparable<User>{
    private String name;
    private String id;
    private boolean hasCorona;
    private LocalDateTime timestamp;
    private List<ILocation> iLocations;

    public User(String name, String id) {
        this.name = name;
        this.id = id;
        hasCorona = false;
        timestamp = null;
        iLocations = new ArrayList<>();
    }

    public boolean hasCorona() {
        return hasCorona;
    }

    public void setHasCorona(LocalDateTime timestamp) {
        this.hasCorona = true;
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public List<ILocation> getILocations() {
        return iLocations;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setILocations(List<ILocation> iLocations) {
        //System.out.println(String.format("ADDED LOCATIONS %d", iLocations.size()));
        this.iLocations.addAll(iLocations);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    public int wasInDirectContactWith(User u) {
        if(this.equals(u)){
            return 0;
        }
        int amountOfDirectContacts = 0;

        for(int i=0; i<this.iLocations.size(); i++){

            for(int j=0; j<u.iLocations.size(); j++){
                double x = Math.pow(iLocations.get(i).getLatitude() - u.iLocations.get(j).getLatitude(), 2);
                double y = Math.pow(iLocations.get(i).getLongitude() - u.iLocations.get(j).getLongitude(), 2);
                double minute = Math.abs(iLocations.get(i).getTimestamp().getMinute() - u.iLocations.get(j).getTimestamp().getMinute());
                double year = Math.abs(iLocations.get(i).getTimestamp().getYear() - u.iLocations.get(j).getTimestamp().getYear());
                double month = Math.abs(iLocations.get(i).getTimestamp().getMonthValue() - u.iLocations.get(j).getTimestamp().getMonthValue());
                double day = Math.abs(iLocations.get(i).getTimestamp().getDayOfYear() - u.iLocations.get(j).getTimestamp().getDayOfYear());

                if(Math.sqrt(x + y) <= 2.0 && minute < 5 && year == 0 && month == 0 && day == 0){
                    amountOfDirectContacts++;
                }
            }
        }
        return amountOfDirectContacts;
    }

    @Override
    public int compareTo(User o) {
        return 0;
    }

    @Override
    public String toString() {
        return name + " " + id + " " + timestamp + "\n";
    }

    public String shortPrint() {
        return name + " " + String.format("%s***", id.substring(0,4));
    }
}

class StopCoronaApp{
    Map<String, User> usersById;
    Map<User, List<ILocation>> locationsByUser;
    Set<User> usersWithCorona;


    public StopCoronaApp() {
        usersById = new HashMap<>();
        //locationsByUser = new HashMap<>();
        usersWithCorona = new TreeSet<>(Comparator.comparing(User::getTimestamp));
    }
    void addUser(String name, String id) throws UserIdAlreadyExistsException {
        User user = new User(name, id);
        if(usersById.putIfAbsent(id, user) != null){
            throw new UserIdAlreadyExistsException();
        }
        //locationsByUser.putIfAbsent(user, new ArrayList<>());
    }

    void addLocations (String id, List<ILocation> iLocations){
        //locationsByUser.get(usersById.get(id)).addAll(iLocations);
        usersById.get(id).setILocations(iLocations);
    }

    void detectNewCase (String id, LocalDateTime timestamp){
        usersById.get(id).setHasCorona(timestamp);
        usersWithCorona.add(usersById.get(id));
    }

    Map<User, Integer> getDirectContacts (User u){
        Map<User, Integer> directContacts = new TreeMap<>(Comparator.comparing(user -> user.wasInDirectContactWith(u)));
        List<User> users = usersById.values().stream()
                .filter(user -> user.wasInDirectContactWith(u) > 0)
                .collect(Collectors.toList());
                //.collect(Collectors.groupingBy(user -> user.wasInDirectContactWith(u)));
        for(int i=0; i<users.size(); i++){
            directContacts.put(users.get(i), users.get(i).wasInDirectContactWith(u));
        }
        return directContacts;
    }

    Collection<User> getIndirectContacts (User u){
        return getDirectContacts(u).keySet().stream()
                .map(this::getDirectContacts)
                .map(Map::keySet)
                .reduce(new TreeSet<>(Comparator.comparing(User::getName).thenComparing(User::getId)), (users, users2) -> Stream.concat(users.stream().filter(user -> !user.equals(u)), users2.stream().filter(user -> !user.equals(u))).collect(Collectors.toSet()));
    }

    void createReport (){
        StringBuilder sb = new StringBuilder();
        AtomicReference<Integer> integer = new AtomicReference<>(0);
        AtomicReference<Integer> integer1 = new AtomicReference<>(0);
        usersWithCorona.stream()
                .forEach(user -> {
                    sb.append(user);
                    sb.append("Direct contacts:\n");
                    getDirectContacts(user).entrySet().stream()
                            .forEach(userIntegerEntry -> {
                                integer.updateAndGet(v -> v + userIntegerEntry.getValue());
                                sb.append(userIntegerEntry.getKey().shortPrint()).append(" ").append(userIntegerEntry.getValue()).append("\n");
                            });
                    sb.append(String.format("Count of direct contacts: %d\n", integer.get()));
                    sb.append("Indirect contacts:\n");
                    getIndirectContacts(user).stream()
                            .forEach(user1 -> {
                                integer1.getAndSet(integer1.get() + 1);
                                sb.append(user1.shortPrint()).append("\n");
                            });
                    sb.append(String.format("Count of indirect contacts: %d\n", integer1.get()));
                });

        System.out.println(sb);
    }
}


public class StopCoronaTest {

    public static double timeBetweenInSeconds(ILocation location1, ILocation location2) {
        return Math.abs(Duration.between(location1.getTimestamp(), location2.getTimestamp()).getSeconds());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        StopCoronaApp stopCoronaApp = new StopCoronaApp();

        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] parts = line.split("\\s+");

            switch (parts[0]) {
                case "REG": //register
                    String name = parts[1];
                    String id = parts[2];
                    try {
                        stopCoronaApp.addUser(name, id);
                    } catch (UserIdAlreadyExistsException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "LOC": //add locations
                    id = parts[1];
                    List<ILocation> locations = new ArrayList<>();
                    for (int i = 2; i < parts.length; i += 3) {
                        locations.add(createLocationObject(parts[i], parts[i + 1], parts[i + 2]));
                    }
                    stopCoronaApp.addLocations(id, locations);

                    break;
                case "DET": //detect new cases
                    id = parts[1];
                    LocalDateTime timestamp = LocalDateTime.parse(parts[2]);
                    stopCoronaApp.detectNewCase(id, timestamp);

                    break;
                case "REP": //print report
                    stopCoronaApp.createReport();
                    break;
                default:
                    break;
            }
        }
    }

    private static ILocation createLocationObject(String lon, String lat, String timestamp) {
        return new ILocation() {
            @Override
            public double getLongitude() {
                return Double.parseDouble(lon);
            }

            @Override
            public double getLatitude() {
                return Double.parseDouble(lat);
            }

            @Override
            public LocalDateTime getTimestamp() {
                return LocalDateTime.parse(timestamp);
            }
        };
    }
}

