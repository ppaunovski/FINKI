package lab6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShakerSort {

    static void shakerSort(int a[], int n)
    {
        // Vasiot kod tuka
        boolean swapped = true;
        int i = 0;
        while(swapped){
            swapped = false;
            for(int j=n-1-i; j>i; j--){
                if(a[j] < a[j-1]){
                    int tmp = a[j];
                    a[j] = a[j-1];
                    a[j-1] = tmp;
                    swapped = true;
                }
            }

            for(int j=0;j<n;j++){
                 System.out.print(a[j] + " ");
             }
             System.out.println();
            for(int k = i; k<n-1-i; k++){
                if(a[k] > a[k+1]){
                    int tmp = a[k];
                    a[k] = a[k+1];
                    a[k+1] = tmp;
                    swapped = true;
                }
            }

            for(int j=0;j<n;j++){
                System.out.print(a[j] + " ");
            }
            System.out.println();
            if(!swapped)
                break;
            i++;
        }
    }

    public static void main(String[] args) throws IOException{
        int i;
        BufferedReader stdin = new BufferedReader( new InputStreamReader(System.in));
        String s = stdin.readLine();
        int n = Integer.parseInt(s);

        s = stdin.readLine();
        String [] pom = s.split(" ");
        int [] a = new int[n];
        for(i=0;i<n;i++)
            a[i]=Integer.parseInt(pom[i]);
        shakerSort(a,n);
    }
}