package Kolokviumski1.bus;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bus {

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int adults = Integer.parseInt(br.readLine());
        //br vozrasni
        int children = Integer.parseInt(br.readLine());
        //br deca

        br.close();

        // Vasiot kod tuka

        int min = 0;
        int max = 0;

        if(children - adults >= 0){
            min = (children-adults)*100 + adults*100;
        }
        else {
            min = adults * 100;
        }

        if(children > 0){
            max = (children+adults-1)*100;
        }
        else
            max = adults*100;
        System.out.println(min);
        System.out.println(max);

    }

}
