package lab7.apteka;

import java.util.Objects;

public class Lek implements Comparable<Lek> {
    String name;
    int zaliha;
    int cena;
    int total;

    public Lek(String name, int zaliha, int cena, int total) {
        this.name = name;
        this.zaliha = zaliha;
        this.cena = cena;
        this.total = total;
    }

    public static Lek createLek(String line){
        String[] parts = line.split("\\s+");

        return new Lek(parts[0].toUpperCase(), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lek lek = (Lek) o;
        return zaliha == lek.zaliha && cena == lek.cena && total == lek.total && Objects.equals(name, lek.name);
    }

    @Override
    public String toString() {
        String poz = this.zaliha == 1 ? "POZ" : "NEG";
        return this.name + "\n" + poz + "\n" + this.cena + "\n" + this.total + "\n";
    }

    @Override
    public int compareTo(Lek o) {
        //return this.name.compareTo(o.name);
        return Integer.compare(this.cena, o.cena);
    }

    public void odzemiLekovi(int parseInt) {
        total = total - parseInt;
    }
}
