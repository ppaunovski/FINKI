package Kolokviumki2.kumanovskiDijalekt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class MapEntry<K extends Comparable<K>,E> implements Comparable<K> {

    // Each MapEntry object is a pair consisting of a key (a Comparable
    // object) and a value (an arbitrary object).
    K key;
    E value;

    public MapEntry (K key, E val) {
        this.key = key;
        this.value = val;
    }

    public int compareTo (K that) {
        // Compare this map entry to that map entry.
        @SuppressWarnings("unchecked")
        MapEntry<K,E> other = (MapEntry<K,E>) that;
        return this.key.compareTo(other.key);
    }

    public String toString () {
        return "<" + key + "," + value + ">";
    }
}

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class CBHT<K extends Comparable<K>, E> {

    // An object of class CBHT is a closed-bucket hash table, containing
    // entries of class MapEntry.
    private SLLNode<MapEntry<K,E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {
        // Construct an empty CBHT with m buckets.
        buckets = (SLLNode<MapEntry<K,E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
        // Napishete ja vie HASH FUNKCIJATA

        return buckets.length != 0 ? Math.abs(key.hashCode()) % buckets.length : 0;
    }

    public SLLNode<MapEntry<K,E>> search(K targetKey) {
        // Find which if any node of this CBHT contains an entry whose key is
        // equal
        // to targetKey. Return a link to that node (or null if there is none).
        int b = hash(targetKey);
        if(buckets.length != 0  || b!=0){
            for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
                if (targetKey.equals(((MapEntry<K, E>) curr.element).key))
                    return curr;
            }
        }
        return null;
    }

    public void insert(K key, E val) {		// Insert the entry <key, val> into this CBHT.
        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
        int b = hash(key);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (key.equals(((MapEntry<K, E>) curr.element).key)) {
                // Make newEntry replace the existing entry ...
                curr.element = newEntry;
                return;
            }
        }
        // Insert newEntry at the front of the 1WLL in bucket b ...
        buckets[b] = new SLLNode<MapEntry<K,E>>(newEntry, buckets[b]);
    }

    public void delete(K key) {
        // Delete the entry (if any) whose key is equal to key from this CBHT.
        int b = hash(key);
        for (SLLNode<MapEntry<K,E>> pred = null, curr = buckets[b]; curr != null; pred = curr, curr = curr.succ) {
            if (key.equals(((MapEntry<K,E>) curr.element).key)) {
                if (pred == null)
                    buckets[b] = curr.succ;
                else
                    pred.succ = curr.succ;
                return;
            }
        }
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            temp += i + ":";
            for (SLLNode<MapEntry<K,E>> curr = buckets[i]; curr != null; curr = curr.succ) {
                temp += curr.element.toString() + " ";
            }
            temp += "\n";
        }
        return temp;
    }

}

public class KumanovskiDijalekt {
    public static void main (String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(
                System.in));
        int N = Integer.parseInt(br.readLine());

        String rechnik[]=new String[N];
        for(int i=0;i<N;i++){
            rechnik[i]=br.readLine();
        }

        String tekst=br.readLine();

        //Vasiot kod tuka
        CBHT<String, String> mapa = new CBHT<>(N*2);

        for(int i=0; i<N; i++){
            String []parts = rechnik[i].split(" ");
            mapa.insert(parts[0], parts[1]);
        }

//        String []textWOZapirka = tekst.split(",");
//        List<String> textWOTocka = new ArrayList<>();
//        for(int i=0; i< textWOZapirka.length; i++){
//            String []parts = textWOZapirka[i].split("\\.");
//            textWOTocka.addAll(List.of(parts));
//        }
//        List<String> textWOZnaci = new ArrayList<>();
//        for(int i=0; i< textWOTocka.size(); i++){
//            String []parts = textWOTocka.get(i).split("!");
//            textWOZnaci.addAll(List.of(parts));
//        }

        List<String> zborovi = new ArrayList<>();

        String []parts = tekst.split("\\s+");
        zborovi.addAll(List.of(parts));


//        System.out.println(zborovi);
//        System.out.println(mapa);

        StringBuilder sb = new StringBuilder();

        for(String zbor : zborovi){
            String addThisWord = zbor;
            if(zbor.contains(",")){
                addThisWord = zbor.substring(0, zbor.length()-1);
                if(mapa.search(addThisWord.toLowerCase()) != null){
                    if(Character.isUpperCase(zbor.charAt(0))){
                        String word = mapa.search(addThisWord.toLowerCase()).element.value;
                        addThisWord = String.valueOf(Character.toUpperCase(word.charAt(0))) + word.substring(1);
                        sb.append(addThisWord);
                    }
                    else {
                        sb.append(mapa.search(addThisWord.toLowerCase()).element.value);
                    }
                }
                else {
                    sb.append(addThisWord);
                }
                sb.append(", ");
            }
            else if(zbor.contains(".")){
                addThisWord = zbor.substring(0, zbor.length()-1);
                if(mapa.search(addThisWord.toLowerCase()) != null){
                    if(Character.isUpperCase(zbor.charAt(0))){
                        String word = mapa.search(addThisWord.toLowerCase()).element.value;
                        addThisWord = String.valueOf(Character.toUpperCase(word.charAt(0))) + word.substring(1);
                        sb.append(addThisWord);
                    }
                    else {
                        sb.append(mapa.search(addThisWord.toLowerCase()).element.value);
                    }
                }
                else {
                    sb.append(addThisWord);
                }
                sb.append(". ");
            }
            else if(zbor.contains("!")){
                addThisWord = zbor.substring(0, zbor.length()-1);
                if(mapa.search(addThisWord.toLowerCase()) != null){
                    if(Character.isUpperCase(zbor.charAt(0))){
                        String word = mapa.search(addThisWord.toLowerCase()).element.value;
                        addThisWord = String.valueOf(Character.toUpperCase(word.charAt(0))) + word.substring(1);
                        sb.append(addThisWord);
                    }
                    else {
                        sb.append(mapa.search(addThisWord.toLowerCase()).element.value);
                    }
                }
                else {
                    sb.append(addThisWord);
                }
                sb.append("! ");
            }
            else {
                if(mapa.search(addThisWord.toLowerCase()) != null){
                    if(Character.isUpperCase(zbor.charAt(0))){
                        String word = mapa.search(addThisWord.toLowerCase()).element.value;
                        addThisWord = String.valueOf(Character.toUpperCase(word.charAt(0))) + word.substring(1);
                        sb.append(addThisWord);
                    }
                    else {
                        sb.append(mapa.search(addThisWord.toLowerCase()).element.value);
                    }
                }
                else {
                    sb.append(addThisWord);
                }
                sb.append(" ");
            }
        }
        System.out.println(sb);
    }
}
