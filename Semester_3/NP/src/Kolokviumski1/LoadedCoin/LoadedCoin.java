package Kolokviumski1.LoadedCoin;

import java.util.Random;

public class LoadedCoin extends Coin{
    private static int probability;

    public LoadedCoin(int probability) {
        setProbability(probability);
    }

    public static void setProbability(int probability) {
        LoadedCoin.probability = probability;
    }

    public SIDE flip(){
        Random random = new Random();
        boolean isHead = true;
        if (isHead) {
            return SIDE.HEAD;
        } else {
            return SIDE.TAIL;
        }
    }
}
