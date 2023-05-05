package lab5.zadaca1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

interface Stack<E> {

    // Elementi na stekot se objekti od proizvolen tip.

    // Metodi za pristap:

    public boolean isEmpty ();
    // Vrakja true ako i samo ako stekot e prazen.

    public E peek ();
    // Go vrakja elementot na vrvot od stekot.

    // Metodi za transformacija:

    public void clear ();
    // Go prazni stekot.

    public void push (E x);
    // Go dodava x na vrvot na stekot.

    public E pop ();
    // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
}

class ArrayStack<E> implements Stack<E> {
    private E[] elems;
    private int depth;

    @SuppressWarnings("unchecked")
    public ArrayStack (int maxDepth) {
        // Konstrukcija na nov, prazen stek.
        elems = (E[]) new Object[maxDepth];
        depth = 0;
    }


    public boolean isEmpty () {
        // Vrakja true ako i samo ako stekot e prazen.
        return (depth == 0);
    }


    public E peek () {
        // Go vrakja elementot na vrvot od stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        return elems[depth-1];
    }


    public void clear () {
        // Go prazni stekot.
        for (int i = 0; i < depth; i++)  elems[i] = null;
        depth = 0;
    }


    public void push (E x) {
        // Go dodava x na vrvot na stekot.
        elems[depth++] = x;
    }


    public E pop () {
        // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        E topmost = elems[--depth];
        elems[depth] = null;
        return topmost;
    }
}

public class PostFixEvaluation {

    static int evaluatePostfix(char [] izraz, int n)
    {
        ArrayStack<Integer> numbers = new ArrayStack<>(100);
        for(int i=0; i<izraz.length; i++){
//            if(Character.isDigit(izraz[i])){
//                numbers.
//            }
            int num = -1;
            while (i<izraz.length && !Character.isSpaceChar(izraz[i]) && Character.isDigit(izraz[i])){
                if(num == -1){
                    num = 0;
                }
                num = num * 10 + Character.digit(izraz[i], 10);
                i++;
            }
            if(num != 0){
                numbers.push(num);
            }
            if(!Character.isDigit(izraz[i]) && !Character.isSpaceChar(izraz[i])){
                int megjuZbir = 0;
                int num2 = numbers.pop();
                int num1 = numbers.pop();
                switch (izraz[i]){
                    case '+': megjuZbir = num1 + num2; break;
                    case '-': megjuZbir = num1 - num2; break;
                    case '*': megjuZbir = num1 * num2; break;
                    case '/': megjuZbir = num1 / num2; break;
                }
                //System.out.println(megjuZbir);
                numbers.push(megjuZbir);
            }
            //System.out.println(numbers.peek());
            //System.out.println(num);
        }
        //System.out.println(izraz);
        //System.out.println(numbers.peek());
        return numbers.pop();
    }

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();
        char exp[] = expression.toCharArray();

        int rez = evaluatePostfix(exp, exp.length);
        System.out.println(rez);

        br.close();

    }

}