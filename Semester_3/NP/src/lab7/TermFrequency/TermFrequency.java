package lab7.TermFrequency;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class TermFrequency {
    Map<String, Integer> countPerWord;

    TermFrequency(InputStream inputStream, String[] stopWords){
        countPerWord = new TreeMap<>();
        Scanner sc = new Scanner(inputStream);

        while (sc.hasNextLine()){
            String line = sc.nextLine();
            String []words = line.split("\\s+");

            for(String word : words){
                for(String stop : stopWords){
                    if(word.equals(stop)){
                        word = word.replaceAll(stop, "");
                    }
                    if(word.contains(".")){
                        word = word.replaceAll("\\.", "");
                    }
                    if(word.contains(",")){
                        word = word.replaceAll(",", "");
                    }
                }
                word = word.toLowerCase();
                //System.out.println(word);
                //System.out.println();
                if(!word.equals("") && !word.equals(" ")){
                    countPerWord.putIfAbsent(word, 0);

                    countPerWord.put(word, countPerWord.get(word) + 1);
                }
            }


        }
    }
    public int countTotal(){
        System.out.println(countPerWord);
        return countPerWord.values().stream().mapToInt(i -> i).sum();
    }

    public int countDistinct(){
        return countPerWord.size();
    }

    List<String> mostOften(int k){
        List<String> collect = countPerWord.entrySet().stream()
                .sorted(Comparator.comparing(Entry::getValue))
                .map(Entry::getKey)
                .collect(Collectors.toList());

        List<String> retList = new ArrayList<>();
        for(int i=collect.size()-1; i>=collect.size()-1-k; i--){
            retList.add(collect.get(i));
        }
        return retList;
    }
}


class TermFrequencyTest {
    public static void main(String[] args) throws FileNotFoundException {
        String[] stop = new String[] { "во", "и", "се", "за", "ќе", "да", "од",
                "ги", "е", "со", "не", "тоа", "кои", "до", "го", "или", "дека",
                "што", "на", "а", "но", "кој", "ја" };
        TermFrequency tf = new TermFrequency(System.in,
                stop);
        System.out.println(tf.countTotal());
        System.out.println(tf.countDistinct());
        System.out.println(tf.mostOften(10));
    }
}