package lab1;

import java.util.Arrays;
import java.util.Scanner;

class RabotnaNedela{

    private int [] casovi;
    private int brNedela;

    public RabotnaNedela(int[] casovi, int brNedela) {
        super();
        this.casovi = casovi;
        this.brNedela = brNedela;
    }

     int sumWorkHoursWeekly(){
        int sum = 0;
        for(int i=0; i<casovi.length; i++){
            sum+=casovi[i];
        }
        return sum;
    }
    @Override
    public String toString() {
        return sumWorkHoursWeekly() + "   ";
    }

    public void setCasovi(int[] casovi) {
        this.casovi = casovi;
    }

    public void setBrNedela(int brNedela) {
        this.brNedela = brNedela;
    }
}

class Rabotnik{

    private String ime;
    private RabotnaNedela [] nedeli;

    public Rabotnik(String ime, RabotnaNedela[] nedeli) {
        super();
        this.ime = ime;
        this.nedeli = nedeli;
    }

    public int getTotalMonthly(){
        int sum=0;
        for(int i=0; i< nedeli.length; i++){
            sum += nedeli[i].sumWorkHoursWeekly();
        }
        return sum;
    }

    private String printRabotnaNedelaTotal(){
        String returnString = "";
        for(int i=0; i<nedeli.length; i++){
            returnString += nedeli[i].toString();
        }
        return returnString + getTotalMonthly();
    }

    @Override
    public String toString() {
        return (ime + "   " + printRabotnaNedelaTotal());
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setNedeli(RabotnaNedela[] nedeli) {
        this.nedeli = nedeli;
    }
}

public class Main {

    public static Rabotnik najvreden_rabotnik(Rabotnik [] niza)
    {
        Rabotnik max = niza[0];
        for(int i=0; i< niza.length; i++){
            if(max.getTotalMonthly() < niza[i].getTotalMonthly())
                max = niza[i];
        }
        return max;
    }
    public static void table(Rabotnik [] niza)
    {
        System.out.println("Rab   1   2   3   4   Vkupno");
        for(int i=0; i<niza.length; i++){
            System.out.println(niza[i].toString());
        }
    }

    public static void main(String[] args) {

        int n;
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        Rabotnik [] niza = new Rabotnik[n];
        for(int i=0;i<n;i++)
        {
            //vasiot kod tuka
            String name;
            name = input.next();
            RabotnaNedela []nedeli = new RabotnaNedela[4];
            for(int j=0; j<4; j++){
                int[] casovi = new int[5];
                for(int k=0; k<5; k++){
                    casovi[k] = input.nextInt();
                }
                nedeli[j] = new RabotnaNedela(casovi, j);
//                nedeli[j].setCasovi(casovi);
//                nedeli[j].setBrNedela(j);
            }
            niza[i] = new Rabotnik(name, nedeli);
//            niza[i].setIme(name);
//            niza[i].setNedeli(nedeli);
        }

        table(niza);
        System.out.println("\nNAJVREDEN RABOTNIK: " +najvreden_rabotnik(niza).getIme());

    }
}

