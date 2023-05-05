package lab5.zadaca3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Gragjanin {
    String fullName;
    int idCard;
    int passport;
    int driversLicence;

    public Gragjanin(String fullName, int idCard, int passport, int driversLicence) {
        this.fullName = fullName;
        this.idCard = idCard;
        this.passport = passport;
        this.driversLicence = driversLicence;
    }
}


public class MVR {

    public static void main(String[] args) {

        Scanner br = new Scanner(System.in);

        Queue<Gragjanin> idCardQ = new LinkedList<>();
        Queue<Gragjanin> passportQ = new LinkedList<>();
        Queue<Gragjanin> driversLicenceQ = new LinkedList<>();

        int N = Integer.parseInt(br.nextLine());
        for(int i=1;i<=N;i++){
            String imePrezime = br.nextLine();
            int lKarta = Integer.parseInt(br.nextLine());
            int pasos = Integer.parseInt(br.nextLine());
            int vozacka = Integer.parseInt(br.nextLine());
            Gragjanin covek = new Gragjanin(imePrezime,lKarta,pasos,vozacka);
            if(covek.idCard == 1){
                idCardQ.add(covek);
            }
            else if(covek.passport == 1){
                passportQ.add(covek);
            } else if (covek.driversLicence == 1) {
                driversLicenceQ.add(covek);
            }
        }

        while(!idCardQ.isEmpty()){
            Gragjanin tmp = idCardQ.remove();
            if(tmp.passport == 1){
                passportQ.add(tmp);
            } else if (tmp.driversLicence == 1) {
                driversLicenceQ.add(tmp);
            }
            else {
                System.out.println(tmp.fullName);
            }
        }

        while(!passportQ.isEmpty()){
            Gragjanin tmp = passportQ.remove();
            if (tmp.driversLicence == 1) {
                driversLicenceQ.add(tmp);
            }
            else {
                System.out.println(tmp.fullName);
            }
        }

        while(!driversLicenceQ.isEmpty()){
            Gragjanin tmp = driversLicenceQ.remove();
            System.out.println(tmp.fullName);
        }
    }
}

