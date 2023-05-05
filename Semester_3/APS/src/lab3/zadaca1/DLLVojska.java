package lab3.zadaca1;

import java.util.Scanner;

 class DLLNode<E> {
    protected E element;
    protected DLLNode<E> pred, succ;
    public DLLNode(E elem, DLLNode<E> pred, DLLNode<E> succ) {
        this.element = elem;
        this.pred = pred;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

 class DLL<E> {
    private DLLNode<E> first, last;

    public DLL() {
        // Construct an empty SLL
        this.first = null;
        this.last = null;
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
        } else
            return null;
    }

    public E delete(DLLNode<E> node) {
        if (node == first) {
            return deleteFirst();
        }
        if (node == last) {
            return deleteLast();
        }
        node.pred.succ = node.succ;
        node.succ.pred = node.pred;
        return node.element;

    }

    public DLLNode<E> find(E o) {
        if (first != null) {
            DLLNode<E> tmp = first;
            while (!tmp.element.equals(o) && tmp.succ != null)
                tmp = tmp.succ;
            if (tmp.element.equals(o)) {
                return tmp;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
        return null;
    }

    public void deleteList() {
        first = null;
        last = null;
    }

    public int getSize() {
        int listSize = 0;
        DLLNode<E> tmp = first;
        while(tmp != null) {
            listSize++;
            tmp = tmp.succ;
        }
        return listSize;
    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            DLLNode<E> tmp = first;
            ret += tmp.toString();
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += "<->" + tmp.toString();
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public String toStringR() {
        String ret = new String();
        if (last != null) {
            DLLNode<E> tmp = last;
            ret += tmp.toString();
            while (tmp.pred != null) {
                tmp = tmp.pred;
                ret += "<->" + tmp.toString();
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

    public void setFirst(DLLNode<E> node) {
        this.first = node;
    }

    public void setLast(DLLNode<E> node) {
        this.last = node;
    }

    public void mirror() {

        DLLNode<E> tmp = null;
        DLLNode<E> current = first;
        last = first;
        while(current!=null) {
            tmp = current.pred;
            current.pred = current.succ;
            current.succ = tmp;
            current = current.pred;
        }

        if(tmp!=null && tmp.pred!=null) {
            first=tmp.pred;
        }
    }
}



    public class DLLVojska {

        public static DLL<Integer> vojska(DLL<Integer> lista, int a, int b, int c, int d) {

            //Vasiot kod tuka
            DLLNode<Integer> first1 = lista.find(a);
            DLLNode<Integer> first2 = lista.find(c);
            DLLNode<Integer> last1 = lista.find(b);
            DLLNode<Integer> last2 = lista.find(d);

            if(lista.find(b).succ.equals(lista.find(c))){
                System.out.println("PODINTERVALITE SE ZALEPENI");
                while(first2 != null){
                    lista.insertBefore(first2.element, first1);
                    lista.delete(first2);
                    first2 = first2.succ;
                }
            }
            else{
                System.out.println("IMA ELEMNETI MEGJU PONDINTERVALITE");
                DLLNode<Integer> insertingBeforeThis = lista.find(last1.element).succ;
                DLLNode<Integer> insertingAfterThis = lista.find(first2.element).pred;

                boolean isFirstSubListDone = false;
                boolean isSecondSubListDone = false;
                while(!isSecondSubListDone || !isFirstSubListDone){
                    if(first2 != null && !first2.equals(last2.succ)){
                        lista.insertBefore(first2.element, insertingBeforeThis);
                        lista.delete(first2);
                        first2 = first2.succ;
                    }
                    else {
                        System.out.println("SECOND SUB IS DONE");
                        isSecondSubListDone = true;
                    }
                    if(last1 != null && !last1.equals(first1.pred)){
                        lista.insertAfter(last1.element, insertingAfterThis);
                        lista.delete(last1);
                        last1 = last1.pred;
                    }
                    else {
                        System.out.println("FIRST SUB ID DONE");
                        isFirstSubListDone = true;
                    }
                }
            }
            return lista;
        }


        public static void main(String[] args) {

            Scanner input = new Scanner(System.in);

            int n = input.nextInt();

            DLL<Integer> lista = new DLL<>();
            for(int i=0;i<n;i++) {
                lista.insertLast(input.nextInt());
            }

            int a = input.nextInt();
            int b = input.nextInt();

            int c = input.nextInt();
            int d = input.nextInt();

            DLL<Integer> result = vojska(lista, a, b, c, d);

            System.out.println(result);

        }
    }