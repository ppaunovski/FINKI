package Kolokviumski2.Audition;

import java.util.*;

class Participant{
    String city;
    String code;
    String name;
    int age;

    public Participant(String city, String code, String name, int age) {
        this.city = city;
        this.code = code;
        this.name = name;
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return age == that.age && Objects.equals(city, that.city) && Objects.equals(code, that.code) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, code, name, age);
    }

    @Override
    public String toString() {
        return String.format("%s %s %d", code, name, age);
    }
}


class Audition{
//    Map<String, Participant> participantByCode;
//    Map<String, Set<String>> codesByCity;
    Map<String, Map<String, Participant>> participantByCodeByCity;

    public Audition() {
//        participantByCode = new HashMap<>();
//        codesByCity = new HashMap<>();
        participantByCodeByCity = new HashMap<>();
    }

    void addParticpant(String city, String code, String name, int age){
        Participant participant = new Participant(city, code, name, age);
//        codesByCity.putIfAbsent(city, new HashSet<>());
//        if(codesByCity.get(city).add(code)){
//            participantByCode.put(code, participant);
//        }
//
        participantByCodeByCity.putIfAbsent(city, new HashMap<>());
        if(!participantByCodeByCity.get(city).containsKey(code)){
            participantByCodeByCity.get(city).put(code, participant);
        }
    }

    void listByCity(String city){
//        codesByCity.get(city).stream()
//                .map(code -> participantByCode.get(code))
//                .sorted(Comparator.comparing(Participant::getName).thenComparing(Participant::getAge))
//                .forEach(System.out::println);
        participantByCodeByCity.get(city).values().stream()
                .sorted(Comparator.comparing(Participant::getName).thenComparing(Participant::getAge))
                .forEach(System.out::println);
    }
}

public class AuditionTest {
    public static void main(String[] args) {
        Audition audition = new Audition();
        List<String> cities = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            if (parts.length > 1) {
                audition.addParticpant(parts[0], parts[1], parts[2],
                        Integer.parseInt(parts[3]));
            } else {
                cities.add(line);
            }
        }
        for (String city : cities) {
            System.out.printf("+++++ %s +++++\n", city);
            audition.listByCity(city);
        }
        scanner.close();
    }
}