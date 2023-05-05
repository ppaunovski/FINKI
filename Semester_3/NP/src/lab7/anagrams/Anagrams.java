//package lab7.anagrams;
//
//import java.io.InputStream;
//import java.util.*;
//import java.util.stream.Collectors;
//
//class Anagram{
//    List<String > letters;
//
//    public Anagram(String word) {
//        this.letters = Arrays.stream(word.split("")).collect(Collectors.toList());
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Anagram anagram = (Anagram) o;
//        return Objects.equals(letters, anagram.letters);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(letters);
//    }
//
//}
//
//class Word implements Comparable<Word>{
//    String word;
//    Map<String, Integer> countPerLetter;
//
//    public Word(String word) {
//        this.word = word;
//        countPerLetter = new TreeMap<>();
//        fillMap();
//    }
//    private void fillMap() {
//        String []arr = word.split("");
//        for(int i=0; i<arr.length; i++){
//            if(countPerLetter.containsKey(arr[i])){
//                int prev = countPerLetter.get(arr[i]);
//                countPerLetter.put(arr[i], prev+1);
//            }
//            else {
//                countPerLetter.put(arr[i], 1);
//            }
//        }
//    }
//
//    public int getLength(){
//        return word.length();
//    }
//
//    public boolean isAnagramWith(Word a){
//        if(this.countPerLetter.size() != a.countPerLetter.size()){
//            return false;
//        }
//
//        List<String> keySet1 = countPerLetter.keySet().stream().collect(Collectors.toList());
//        List<String> keySet2 = a.countPerLetter.keySet().stream().collect(Collectors.toList());
//        for(int i=0; i<countPerLetter.size(); i++){
//            if(!keySet1.get(i).equals(keySet2.get(i))){
//                return false;
//            }
//        }
//
//        String []arr = word.split("");
//        for(int i=0; i<countPerLetter.size(); i++){
//            if(!this.countPerLetter.get(arr[i]).equals(a.countPerLetter.get(arr[i]))){
//                return false;
//            }
//        }
//        return true;
//    }
//
//    @Override
//    public int compareTo(Word o) {
//        return word.compareTo(o.word);
//    }
//
//    @Override
//    public boolean equals(Object o) {
//
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Word word = (Word) o;
//        return isAnagramWith(word);
////        return Objects.equals(word, anagram.word) && Objects.equals(countPerLetter, anagram.countPerLetter);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(word, countPerLetter);
//    }
//}
//
//public class Anagrams {
//
//    public static void main(String[] args) {
//        findAll(System.in);
//    }
//
//    public static void findAll(InputStream inputStream) {
//        // Vasiod kod ovde
//        Map<Anagram, List<String>> wordsByAnagram = new HashMap<>();
//        List<Anagram> anagrams = new ArrayList<>();
//
//        Scanner sc = new Scanner(inputStream);
//
//        while (sc.hasNextLine()){
//            String line = sc.nextLine();
//            Word word = new Word(line);
//            Anagram anagram = new Anagram(word);
//            System.out.println(wordsByAnagram.putIfAbsent(anagram, new ArrayList<>()));
//
//            wordsByAnagram.get(anagram).add(word);
//        }
//
//        wordsByAnagram.values().stream()
//                .forEach(System.out::println);
//
////        Anagram a1 = new Anagram(sc.nextLine());
////        Anagram a2 = new Anagram(sc.nextLine());
////
////        System.out.println(a1.equals(a2));
//    }
//}