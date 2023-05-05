package Kolokviumski1.MaxProduct;

public class MaxProduct {

    public static int maxProduct(int []array){
        int maxProduct = 1;
        int []maxArray = new int[array.length];
        for(int i=0; i<array.length; i++){
            maxArray[i] = 1;
        }
        maxArray[0] = array[0];
        for(int i=1; i<array.length; i++){

            for(int j=0; j<i; j++){
                if(array[i] > array[j]){
                    if(maxArray[i] < maxArray[j] * array[i]){
                        maxArray[i] = maxArray[j] * array[i];
                        if(maxProduct < maxArray[i]){
                            maxProduct = maxArray[i];
                        }
                    }
                }
            }
        }
        return maxProduct;
    }

    public static void main(String[] args) {
        int []arr = {2, 8, 1, 6, 7, 4, 15, 5};
        System.out.println(maxProduct(arr));
    }
}
