package Kolokviumski2.UnikatniIminja;

import java.util.*;
import java.util.stream.Collectors;

class Names{
    Map<String, Integer> names;

    public Names() {
        names = new TreeMap<>();
    }

    public void addName(String name){
        names.putIfAbsent(name, 0);
        names.put(name, names.get(name)+1);
    }

    public void printN(int n){
        names.entrySet().stream()
                .filter(stringIntegerEntry -> stringIntegerEntry.getValue() >= n)
                .forEach(stringIntegerEntry -> System.out.println(String.format("%s (%d) %d", stringIntegerEntry.getKey(), stringIntegerEntry.getValue(), uniqueLetters(stringIntegerEntry.getKey()))));
    }

    public String findName(int len, int x){
        names = names.entrySet().stream()
                .filter(stringIntegerEntry -> stringIntegerEntry.getKey().length() < len)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        int size = names.keySet().size();
        int newX = x%size;
        Iterator<String> iterator = names.keySet().stream().sorted(Comparator.naturalOrder()).iterator();
        String retString = "";
        for(int i=0; i<newX; i++){
            retString = iterator.next();
        }
        return iterator.next();
    }

    private int uniqueLetters(String key) {
        Set<String> uniqueLetter = new HashSet<>(Arrays.asList(key.toLowerCase().split("")));
        return uniqueLetter.size();
    }
}

public class NamesTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        Names names = new Names();
        for (int i = 0; i < n; ++i) {
            String name = scanner.nextLine();
            names.addName(name);
        }
        n = scanner.nextInt();
        System.out.printf("===== PRINT NAMES APPEARING AT LEAST %d TIMES =====\n", n);
        names.printN(n);
        System.out.println("===== FIND NAME =====");
        int len = scanner.nextInt();
        int index = scanner.nextInt();
        System.out.println(names.findName(len, index));
        scanner.close();

    }
}

