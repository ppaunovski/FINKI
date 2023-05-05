package Kolokviumski1.SortDLL;


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

    public void sort() {
        DLLNode<E> prev, node, tmpS, tmpP;
        for(int i=0; i<this.length(); i++){
            prev = first;
            node = first.succ;
            while (node != null){
                if(node.element.compareTo(prev.element) < 0){
                    tmpS = node.succ;
                    tmpP = prev.pred;

                    node.succ = prev;
                    prev.succ = tmpS;

                    node.pred = prev.pred;
                    prev.pred = node;

                    if(tmpP != null){
                        tmpP.succ = node;
                    }
                    if(tmpS != null){
                        tmpS.pred = prev;
                    }
                    if(prev == first){
                        first = node;
                    }
                    if(node == last){
                        last = prev;
                    }
                    node = tmpS;
                } else {
                    node = node.succ;
                    prev = prev.succ;
                }
            }
        }
    }
}

public class SortDLL {
    public static void main(String[] args) {
        DLL<Integer> dll = new DLL<>();
        for(int i=0; i<10; i++){
            Integer value = (int) Math.floor(Math.random()*100);
            //SLLNode<Integer> node = new SLLNode<>(value, null);
            dll.insertFirst(value);
        }
        System.out.println(dll);

        dll.sort();

        System.out.println(dll);
    }
}
