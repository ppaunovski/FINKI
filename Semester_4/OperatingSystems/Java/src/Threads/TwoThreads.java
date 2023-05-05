package Threads;

import java.util.ArrayList;
import java.util.List;

public class TwoThreads {


    public static void main(String[] args) throws InterruptedException {
//        ThreadClassLetters letters = new ThreadClassLetters();
//        ThreadClassNumbers numbers = new ThreadClassNumbers();
//        letters.start();
//        letters.join();
//        numbers.start();
//        numbers.join();

        List<String> l = new ArrayList<>();
        List<String> n = new ArrayList<>();
        for(int i = 0; i<10;i++) l.add(String.format("%s",(char)(i + 65)));
        for(int i = 0; i<10;i++) n.add(String.format("%d", i));
        //ThreadClassLettersNumbers letters = new ThreadClassLettersNumbers(l);
        //ThreadClassLettersNumbers numbers = new ThreadClassLettersNumbers(n);
        Thread letters = new Thread(new ThreadClassLettersNumbers(l));
        Thread numbers = new Thread(new ThreadClassLettersNumbers(n));
        letters.start();
        letters.join();
        numbers.start();
        numbers.join();

    }


}

class ThreadClassLettersNumbers implements Runnable{
    List<String> list;

    public ThreadClassLettersNumbers(List<String> list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (String str : list) {
            System.out.println(str);
        }
    }
}

class ThreadClassNumbers extends Thread {

    @Override
    public void run() {
        for(int i = 0; i<10;i++) System.out.println(i);
    }
}


class ThreadClassLetters extends Thread {

    @Override
    public void run() {
        for(int i = 0; i<10;i++) System.out.println((char)(i + 65));
    }
}
