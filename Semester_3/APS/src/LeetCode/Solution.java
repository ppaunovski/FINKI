package LeetCode;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'poisonousPlants' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY p as parameter.
     */

    public static int poisonousPlants(List<Integer> p) {
        // Write your code here
        int count = 0;
        List<Integer> tmp = new ArrayList<>();
        List<Integer> indexToDelete;
        Map<Integer, Boolean> indexToD = new HashMap<>();

        while(!isNotIncreasing(p)){
            //System.out.println(p);
            indexToDelete = new ArrayList<>();
            indexToD = new HashMap<>();
            tmp = new ArrayList<>();
            for(int i=0; i<p.size()-1; i++){
                if(p.get(i) < p.get(i+1)){
                    indexToD.put(i+1, true);
                    indexToDelete.add(i+1);
                }
            }
            int index = 0;
            if(indexToDelete.size() > 0){
                for(int i=0; i<p.size(); i++){
                    if(indexToD.get(i) != null){
                        p.remove(i);
                    }
                    else {
                        index++;
                    }
                }
            }
            //p = List.copyOf(tmp);
            count++;
        }
        return count;
    }

    private static boolean isNotIncreasing(List<Integer> list){
        for(int i=0; i<list.size()-1; i++){
            if(list.get(i) < list.get(i+1))
                return false;
        }
        return true;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> p = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = Result.poisonousPlants(p);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
