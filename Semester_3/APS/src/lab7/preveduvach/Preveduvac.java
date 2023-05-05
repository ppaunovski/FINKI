package lab7.preveduvach;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
        return this.key.compareTo(that);
    }

    public String toString () {
        return "<" + key + "," + value + ">";
    }
}

class OBHT<K extends Comparable<K>,E> {

    // An object of class OBHT is an open-bucket hash table, containing entries
    // of class MapEntry.
    private ArrayList<MapEntry<K, E>> buckets;
    private int capacity;

    // buckets[b] is null if bucket b has never been occupied.
    // buckets[b] is former if bucket b is formerly-occupied
    // by an entry that has since been deleted (and not yet replaced).

    static final int NONE = -1; // ... distinct from any bucket index.

    private static final MapEntry former = new MapEntry(null, null);
    // This guarantees that, for any genuine entry e,
    // e.key.equals(former.key) returns false.

    private int occupancy = 0;
    // ... number of occupied or formerly-occupied buckets in this OBHT.

    public OBHT (int m) {
        // Construct an empty OBHT with m buckets.
        buckets = new ArrayList<>(m);
        this.capacity = m;
    }


    private int hash (K key) {
        // Translate key to an index of the array buckets.
        return Math.abs(key.hashCode()) % capacity;
    }

    public MapEntry<K, E> get(int i){
        return buckets.get(i);
    }
    public int search (K targetKey) {
        // Find which if any bucket of this OBHT is occupied by an entry whose key
        // is equal to targetKey. Return the index of that bucket.
        int b = hash(targetKey); int n_search=0;
        for (;;) {
            MapEntry<K,E> oldEntry = buckets.get(b);
            if (oldEntry == null)
                return NONE;
            else if (targetKey.equals(oldEntry.key))
                return b;
            else
            {
                b = (b + 1) % capacity;
                n_search++;
                if(n_search==capacity)
                    return NONE;

            }
        }
    }


    public void insert (K key, E val) {
        // Insert the entry <key, val> into this OBHT.
        MapEntry<K,E> newEntry = new MapEntry<K,E>(key, val);
        int b = hash(key); int n_search=0;
        for (;;) {
            MapEntry<K,E> oldEntry = buckets.get(b);
            if (oldEntry == null) {
                if (++occupancy == capacity) {
                    System.out.println("Hash tabelata e polna!!!");
                }
                buckets.set(b, newEntry);
                return;
            }
            else if (oldEntry == former
                    || key.equals(oldEntry.key)) {
                buckets.set(b, newEntry);
                return;
            }
            else
            {
                b = (b + 1) % capacity;
                n_search++;
                if(n_search==capacity)
                    return;

            }
        }
    }

    @SuppressWarnings("unchecked")
    public void delete (K key) {
        // Delete the entry (if any) whose key is equal to key from this OBHT.
        int b = hash(key); int n_search=0;
        for (;;) {
            MapEntry<K,E> oldEntry = buckets.get(b);

            if (oldEntry == null)
                return;
            else if (key.equals(oldEntry.key)) {
                buckets.set(b, former);//(MapEntry<K,E>)former;
                return;
            }
            else{
                b = (b + 1) % capacity;
                n_search++;
                if(n_search==capacity)
                    return;

            }
        }
    }


    public String toString () {
        String temp = "";
        for (int i = 0; i < capacity; i++) {
            temp += i + ":";
            if (buckets.get(i) == null)
                temp += "\n";
            else if (buckets.get(i) == former)
                temp += "former\n";
            else
                temp += buckets.get(i) + "\n";
        }
        return temp;
    }


    public OBHT<K,E> clone () {
        OBHT<K,E> copy = new OBHT<K,E>(capacity);
        for (int i = 0; i < capacity; i++) {
            MapEntry<K,E> e = buckets.get(i);
            if (e != null && e != former)
                copy.buckets.set(i, new MapEntry<K, E>(e.key, e.value));
            else
                copy.buckets.set(i, e);
        }
        return copy;
    }
}


public class Preveduvac {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        OBHT<String, String> table = new OBHT<>(n*2);

        for(int i=0; i<n; i++){
            String line = br.readLine();
            String []parts = line.split(" ");

            table.insert(parts[1], parts[0]);
        }

        while (true){
            String line = br.readLine();
            if(line.equals("KRAJ")) break;
            if(table.search(line) != -1){
                System.out.println(table.get(table.search(line)).value);
            }
            else System.out.println("/");
        }
    }
}
