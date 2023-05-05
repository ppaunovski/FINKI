package lab6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



class DLLNode<E extends Comparable<E>> {
    protected E element;
    protected DLLNode<E> pred, succ;

    public DLLNode(E elem, DLLNode<E> pred, DLLNode<E> succ) {
        this.element = elem;
        this.pred = pred;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return "<-" + element.toString() + "->";
    }
}

class DLL<E extends Comparable<E>> {
    private DLLNode<E> first, last;

    public DLL() {
        // Construct an empty SLL
        this.first = null;
        this.last = null;
    }

    public void deleteList() {
        first = null;
        last = null;
    }

    public int length() {
        int ret;
        if (first != null) {
            DLLNode<E> tmp = first;
            ret = 1;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret++;
            }
            return ret;
        } else
            return 0;

    }

    public void insertFirst(E o) {
        DLLNode<E> ins = new DLLNode<E>(o, null, first);
        if (first == null)
            last = ins;
        else
            first.pred = ins;
        first = ins;
    }

    public void insertLast(E o) {
        if (first == null)
            insertFirst(o);
        else {
            DLLNode<E> ins = new DLLNode<E>(o, last, null);
            last.succ = ins;
            last = ins;
        }
    }

    public void insertAfter(E o, DLLNode<E> after) {
        if (after == last) {
            insertLast(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, after, after.succ);
        after.succ.pred = ins;
        after.succ = ins;
    }

    public void insertBefore(E o, DLLNode<E> before) {
        if (before == first) {
            insertFirst(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, before.pred, before);
        before.pred.succ = ins;
        before.pred = ins;
    }

    public E deleteFirst() {
        if (first != null) {
            DLLNode<E> tmp = first;
            first = first.succ;
            if (first != null) first.pred = null;
            if (first == null)
                last = null;
            return tmp.element;
        } else
            return null;
    }

    public E deleteLast() {
        if (first != null) {
            if (first.succ == null)
                return deleteFirst();
            else {
                DLLNode<E> tmp = last;
                last = last.pred;
                last.succ = null;
                return tmp.element;
            }
        }
        // else throw Exception
        return null;
    }

    public void setFirst(DLLNode<E> first) {
        this.first = first;
    }

    public void setLast(DLLNode<E> last) {
        this.last = last;
    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            DLLNode<E> tmp = first;
            ret += tmp + "<->";
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += tmp + "<->";
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public DLLNode<E> getFirst() {
        return first;
    }

    public DLLNode<E> getLast() {

        return last;
    }


}

public class BubbleSortDLL {

    public static void bubbleSortDLL(DLL<Integer> dll, int n){
        DLLNode<Integer> prev, node, tmpS, tmpP;

        for(int i=0; i<n; i++){
            prev = dll.getFirst();
            node = dll.getFirst().succ;

            while(node != null){
                if(node.element.compareTo(prev.element) < 0){
                    tmpS = node.succ;
                    tmpP = prev.pred;

                    node.succ = prev;
                    prev.succ = tmpS;

                    prev.pred = node;
                    node.pred = tmpP;

                    if(tmpP != null){
                        tmpP.succ = node;
                    }

                    if(tmpS != null){
                        tmpS.pred = prev;
                    }

                    if(node == dll.getLast()){
                        dll.setLast(prev);
                    }

                    if(prev == dll.getFirst()){
                        dll.setFirst(node);
                    }

                    node = prev.succ;
                }
                else {
                    node = node.succ;
                    prev = prev.succ;
                }
            }
        }
    }

    private static DLLNode<Integer> getNode(DLL<Integer> dll, int index) {
        DLLNode<Integer> returnNode = dll.getFirst();
        int i=0;
        while (returnNode != null && i < index){

            i++;
            returnNode = returnNode.succ;
        }
        return returnNode;
    }

    public static void main(String[] args) {
        DLL<Integer> dll = new DLL<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = 0;
        try {
            n = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String line;
        String []parts;
        try {
            line = br.readLine();
            parts = line.split("\\s+");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(int i=0; i<n; i++){
            dll.insertLast(Integer.parseInt(parts[i]));
        }
        bubbleSortDLL(dll, n);
        System.out.println(dll);
    }

}