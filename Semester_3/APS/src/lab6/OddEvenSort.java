package lab6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OddEvenSort {

    static void oddEvenSort(int a[], int n)
    {
        // Vasiot kod tuka
        for(int i=0; i<n; i++){

            for(int j=0; j<n-1; j++){
                if(a[j] % 2 == 0){
                    if(a[j+1] % 2 != 0){
                        int tmp = a[j];
                        a[j] = a[j+1];
                        a[j+1] = tmp;
                    }
                    else if(a[j] < a[j+1]){
                        int tmp = a[j];
                        a[j] = a[j+1];
                        a[j+1] = tmp;
                    }
                }
                else{
                    if(a[j+1] % 2 != 0 && a[j] > a[j+1]){
                        int tmp = a[j];
                        a[j] = a[j+1];
                        a[j+1] = tmp;
                    }
                }
            }
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
        oddEvenSort(a,n);
        for(i=0;i<n-1;i++)
            System.out.print(a[i]+" ");
        System.out.print(a[i]);
    }
}