package lab7.apteka;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

public class Apteka {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        CBHTApteka<String, Lek> table = new CBHTApteka<>(102780);

        for(int i=0; i<N; i++){
            String line = br.readLine();
            String []parts = line.split("\\s+");
            Lek lek = new Lek(parts[0].toUpperCase(), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));

            table.insert(parts[0].toUpperCase(), lek);
        }
        String line;
        while(!(line = br.readLine()).equals("KRAJ")){
            String imeLek = line.toUpperCase();
            String kolicinaLek = br.readLine();


            if(table.search(imeLek) != null){
                if(table.search(imeLek).element.value.total < Integer.parseInt(kolicinaLek)){
                    System.out.println(table.search(imeLek).element.value.toString());
                    System.out.println("Nema dovolno lekovi");
                }
                else{
                    System.out.println(table.search(imeLek).element.value.toString());
                    System.out.println("Napravena nracka");
                    table.search(imeLek).element.value.odzemiLekovi(Integer.parseInt(kolicinaLek));
                }
            }
            else System.out.println("Nema takov lek");
        }
    }
}
