package lab4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SumOfAbsoluteDifferences {

    static int solve(int numbers[], int N, int K) {
        // vasiot kod ovde
        // mozete da napisete i drugi funkcii dokolku vi se potrebni

        int maxSum = 0;
        int [][]table = new int[K][N];

        //Ne moze nula da se odberat
        //Site abs razliki od niza od eden el. ke bidat 0
        for(int i = 1; i < K; i++){
            // i odi spored broj na elementi vo podnizata
            for(int j = 1; j < N; j++){
                //j odi po sekoj element vo dadenata niza
                int absDiff = Math.abs(numbers[j] - numbers[j-1]);
                for(int k = 0; k < i; k++){
                    //k iterira od pocetok na dadenata niza do dadenata dolzina
                    //na podpodnizata (do i - se cuvaat i megju zbirovi se do K)
                    if(table[i][j] < (table[i-1][j-1] + absDiff))
                        table[i][j] = table[i-1][j-1] + absDiff;
                    if(maxSum < table[i][j])
                        maxSum = table[i][j];
                }
            }
        }
        return maxSum;
    }

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int numbers[] = new int[N];

        st = new StringTokenizer(br.readLine());
        for (i=0;i<N;i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int res = solve(numbers, N, K);
        System.out.println(res);

        br.close();

    }

}