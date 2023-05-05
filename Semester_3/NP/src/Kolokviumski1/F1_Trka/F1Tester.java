package Kolokviumski1.F1_Trka;

import java.io.*;
import java.util.Scanner;

public class F1Tester {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\pauno\\IdeaProjects\\NP\\src\\Kolokviumski1\\F1_Trka\\test_f1.txt");
        F1Race f1Race = new F1Race();
        try {
            f1Race.readResults(new FileInputStream(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            f1Race.printSorted(System.out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
