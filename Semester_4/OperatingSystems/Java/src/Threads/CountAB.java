package Threads;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CountAB {

    public static int NUM_RUNS = 100;
    /**
     * Promenlivi koja treba da go sodrzat brojot na pojavuvanja na karakterite A i B.
     */
    int countA = 0;
    int countB = 0;

    /**
     * Promenliva koja treba da go sodrzi prosecniot brojot na pojavuvanja na karakterite A i B.
     */
    double average = 0.0;
    /**
     * TODO: definirajte gi potrebnite elementi za sinhronizacija
     */
    Lock lockCountA;
    Lock lockCountB;

    public void init() {
        lockCountA = new ReentrantLock();
        lockCountB = new ReentrantLock();
    }

    class CounterA extends Thread {

        public void count(int[] data) throws InterruptedException {
            // da se implementira
            int localCounterA = 0;
            for(int num : data){
                if(num == 'A')
                    localCounterA++;
            }

            lockCountA.lock();
            countA += localCounterA;
            lockCountA.unlock();
        }

        private int[] data;

        public CounterA(int[] data) {
            this.data = data;
        }

        @Override
        public void run() {
            try {
                count(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class CounterB extends Thread {
        public void count(int[] data) throws InterruptedException {
            // da se implementira
            int localCounterB = 0;
            for(int num : data){
                if(num == 'B')
                    localCounterB++;
            }

            lockCountB.lock();
            countB += localCounterB;
            lockCountB.unlock();
        }

        private int[] data;

        public CounterB(int[] data) {
            this.data = data;
        }

        @Override
        public void run() {
            try {
                count(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            CountAB environment = new CountAB();
            environment.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void start() throws Exception {

        init();

        HashSet<Thread> threads = new HashSet<Thread>();
        Scanner s = new Scanner(System.in);
        int total=s.nextInt();
        Random randomInt = new Random();

        for (int i = 0; i < NUM_RUNS; i++) {
            int[] data = new int[total];
            for (int j = 0; j < total; j++) {
                data[j]=(char)(randomInt.nextInt(26) + 65);
                //data[j] = s.nextInt();
            }
            CounterA c = new CounterA(data);
            threads.add(c);
        }

        for (int i = 0; i < NUM_RUNS; i++) {
            int[] data = new int[total];
            for (int j = 0; j < total; j++) {
                data[j]=(char)(randomInt.nextInt(26) + 65);
//                data[j] = s.nextInt();
            }
            CounterB c = new CounterB(data);
            threads.add(c);
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
        average = (countA+countB)/2.0;
        System.out.println(average);


    }
}
