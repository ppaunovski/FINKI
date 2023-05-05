package lab4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ZigZagSequence {

    private static int getSign(int num){
        if(num < 0){
            return -1;
        }
        return 1;
    }

    static int najdiNajdolgaCikCak(int a[]) {
        // Vasiot kod tuka
        int count = 1;
        int maxLength = 1;
        for(int i=1; i<a.length; i++){
            if(getSign(a[i]) != getSign(a[i-1])){
                count++;
            }
            else{
                if(maxLength < count)
                    maxLength = count;
                count = 0;
            }
        }
        if(maxLength < count) maxLength = count;
        return maxLength;
    }

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int a[] = new int[N];
        for (i=0;i<N;i++)
            a[i] = Integer.parseInt(br.readLine());

        int rez = najdiNajdolgaCikCak(a);
        System.out.println(rez);

        br.close();

    }

}
