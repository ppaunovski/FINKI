package lab3.zadaca3;

import java.util.Arrays;
import java.util.Scanner;

public class GreedyCoins {

    public static int minNumCoins(int coins[], int sum) {
        //Vasiot kod ovde
        int coinCount = 0;
        Arrays.sort(coins);
        for(int i=coins.length-1; i>=0; i++){
            if(sum / coins[i] > 0){
                coinCount += sum / coins[i];
                sum /= coins[i];
            }
        }
        return coinCount;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String coinsStringLine = input.nextLine();
        String coinsString[] = coinsStringLine.split(" ");
        int coins[] = new int[coinsString.length];
        for(int i=0;i<coinsString.length;i++) {
            coins[i] = Integer.parseInt(coinsString[i]);
        }

        int sum = input.nextInt();

        System.out.println(minNumCoins(coins, sum));
    }
}