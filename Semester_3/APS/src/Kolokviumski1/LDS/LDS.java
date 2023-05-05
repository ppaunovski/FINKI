package Kolokviumski1.LDS;

import java.util.Arrays;
import java.util.Scanner;


public class LDS {


    private static int najdolgaOpagackaSekvenca(int[] a) {

        // Vasiot kod tuka
        int []maxSubarrays = new int [a.length];

        for(int i=0; i<a.length; i++){
            maxSubarrays[i] = 1;
        }

        for(int i=0; i<a.length; i++){
            for(int j=0; j<i; j++){
                if(a[i] < a[j]){
                    maxSubarrays[i] = Math.max(maxSubarrays[j] + 1, maxSubarrays[i]);
                }
            }
        }

        return Arrays.stream(maxSubarrays).max().orElse(0);

    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int n = stdin.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = stdin.nextInt();
        }
        System.out.println(najdolgaOpagackaSekvenca(a));
    }


}
