package lab1;

import java.util.Scanner;

public class PushZero
{
    static void pushZerosToEnd(int arr[], int n)
    {
        int lastNonZeroIndex = 0;
        for(int i=0; i<n; i++){
            int zeroFound;
            int nextNonZero;


            if(arr[i] == 0){
                int nonZeroIndex = findNextNonZero(arr, i ,n);
                zeroFound = arr[i];
                nextNonZero = arr[nonZeroIndex];
                arr[lastNonZeroIndex] = nextNonZero;
                arr[nonZeroIndex] = zeroFound;

                i = nonZeroIndex;
            }
            lastNonZeroIndex++;
        }
        System.out.println("Transformiranata niza e: ");
        printArray(arr, n);

    }

    static void printArray(int []arr, int n){
        for(int i=0; i<n; i++){

            System.out.printf("%d ", arr[i]);
        }
    }

    private static int findNextNonZero(int arr[], int i, int n){

            while(arr[i] == 0 && i < n){
                ++i;
            }
        return i;
    }

    public static void main (String[] args)
    {
        int arr[] = new int[100];
        int n;


        Scanner scanner = new Scanner(System.in);

        n = scanner.nextInt();

        for(int i=0; i<n; i++){
            arr[i] = scanner.nextInt();
        }

        pushZerosToEnd(arr, n);

    }
}
