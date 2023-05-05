package Kolokviumki2.DedoMrazAdresiNADECA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Hashtable;

class Adresa{
    String ulica;
    String broj;
    String grad;
    String drzava;

    public Adresa(String line) {
        String []parts = line.split("\\s+");

        ulica = parts[0];
        broj = parts[1];
        grad = parts[2];
        drzava = parts[3];
    }

    @Override
    public String toString() {
        return ulica + broj + grad + drzava;
    }
}

public class DedoMrazAdresi {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        HashMap<String , Adresa> adresaHashtable = new HashMap<>();
        HashMap<String, Adresa> hashtable = new HashMap<>();
        for(int i=0; i<n; i++){
            String name = br.readLine();
            String adresa = br.readLine();
            Adresa add = new Adresa(adresa);
            System.out.println(adresa.split("\\s+")[0]);
            adresaHashtable.put(adresa.split("\\s+")[0], add);
            hashtable.put(name, add);
        }
        System.out.println(hashtable);
        int m = Integer.parseInt(br.readLine());

        for(int i=0; i<m; i++){
            String ulici = br.readLine();
            String []parts = ulici.split("\\s+");
            System.out.println(parts[0]);
            if(adresaHashtable.containsKey(parts[0])){

                Adresa stara = adresaHashtable.get(parts[0]);
                stara.ulica = parts[1];
                adresaHashtable.remove(parts[0]);
                adresaHashtable.put(parts[1], stara);
            }
        }
        System.out.println(hashtable);

        String dete = br.readLine();

        if(hashtable.containsKey(dete)){
            System.out.println(hashtable.get(dete));
        }
        else System.out.println("Nema poklon");
    }
}
