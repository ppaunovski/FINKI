//package Kolokviumski1.cardrick;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.*;
//
//
//public class CardTrick {
//    public static int count(int N){
//        Map<String, String> map = new HashMap<>();
//        map.con
//        // Vasiot kod tuka
//        int count = 0;
//        Stack<Integer> stack = new Stack<>();
//        Queue<Integer> queue = new LinkedList<>();
//
//        for(int i=1; i<52; i++){
//            queue.add(i);
//        }
//
//        while (queue.peek() != null && queue.peek() != N){
//            for(int i=0; i<7; i++){
//                stack.push(queue.poll());
//            }
//            while (!stack.isEmpty()){
//                queue.add(stack.pop());
//                queue.add(queue.poll());
//            }
//            count++;
//        }
//        return count;
//    }
//
//    public static void main(String[] args) throws NumberFormatException, IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in) );
//        System.out.println(count(Integer.parseInt(br.readLine())));
//    }
//
//}
