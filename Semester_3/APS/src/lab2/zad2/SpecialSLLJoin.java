package lab2.zad2;

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class SLL<E> {
    private SLLNode<E> first;

    public SLL() {
        // Construct an empty SLL
        this.first = null;
    }

    public void deleteList() {
        first = null;
    }

    public int size() {
        int listSize = 0;
        SLLNode<E> tmp = first;
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
            SLLNode<E> tmp = first;
            ret += tmp;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += " " + tmp;
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public void insertFirst(E o) {
        SLLNode<E> ins = new SLLNode<E>(o, null);
        ins.succ = first;
        //SLLNode<E> ins = new SLLNode<E>(o, first);
        first = ins;
    }

    public void insertAfter(E o, SLLNode<E> node) {
        if (node != null) {
            SLLNode<E> ins = new SLLNode<E>(o, node.succ);
            node.succ = ins;
        } else {
            System.out.println("Dadenot jazol e null");
        }
    }
    public void insertBefore(E o, SLLNode<E> before) {

        if (first != null) {
            SLLNode<E> tmp = first;
            if(first==before){
                this.insertFirst(o);
                return;
            }
            //ako first!=before
            while (tmp.succ != before && tmp.succ!=null)
                tmp = tmp.succ;
            if (tmp.succ == before) {
                tmp.succ = new SLLNode<E>(o, before);;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
    }

    public void insertLast(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            tmp.succ = new SLLNode<E>(o, null);
        } else {
            insertFirst(o);
        }
    }

    public E deleteFirst() {
        if (first != null) {
            SLLNode<E> tmp = first;
            first = first.succ;
            return tmp.element;
        } else {
            System.out.println("Listata e prazna");
            return null;
        }
    }

    public E delete(SLLNode<E> node) {
        if (first != null) {
            SLLNode<E> tmp = first;
            if(first == node) {
                return this.deleteFirst();
            }
            while (tmp.succ != node && tmp.succ.succ != null)
                tmp = tmp.succ;
            if (tmp.succ == node) {
                tmp.succ = tmp.succ.succ;
                return node.element;
            } else {
                System.out.println("Elementot ne postoi vo listata");
                return null;
            }
        } else {
            System.out.println("Listata e prazna");
            return null;
        }

    }

    public SLLNode<E> getFirst() {
        return first;
    }

    public SLLNode<E> find(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
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

    public void merge (SLL<E> in){
        if (first != null) {
            SLLNode<E> tmp = first;
            while(tmp.succ != null)
                tmp = tmp.succ;
            tmp.succ = in.getFirst();
        }
        else{
            first = in.getFirst();
        }
    }

    public void mirror() {
        if (first != null) {
            //m=nextsucc, p=tmp,q=next
            SLLNode<E> tmp = first;
            SLLNode<E> newsucc = null;
            SLLNode<E> next;

            while(tmp != null){
                next = tmp.succ;
                tmp.succ = newsucc;
                newsucc = tmp;
                tmp = next;
            }
            first = newsucc;
        }
    }
}

public class SpecialSLLJoin<E> {

    //todo: implement function
    public SLL<E> specialJoin(SLL<E> list1, SLL<E> list2) {
        SLLNode<E> tmp1 = list1.getFirst();
        SLLNode<E> tmp2 = list2.getFirst();

        SLL<E> returnList = new SLL<>();

        // while(tmp1 != null && tmp2 != null){
        //     returnList.insertLast(tmp1.element);
        //     tmp1 = tmp1.succ;
        //     returnList.insertLast(tmp1.element);
        //     returnList.insertLast(tmp2.element);
        //     tmp2 = tmp2.succ;
        //     returnList.insertLast(tmp2.element);
        //     tmp1 = tmp1.succ;
        //     tmp2 = tmp2.succ;
        // }

        int iteration = 2;

        while(true){
            while(tmp1 != null && iteration > 0){
                returnList.insertLast(tmp1.element);
                tmp1 = tmp1.succ;
                iteration--;
            }
            iteration =2;
            while(tmp2 != null && iteration > 0){
                returnList.insertLast(tmp2.element);
                tmp2 = tmp2.succ;
                iteration--;
            }
            iteration = 2;
             if(tmp1 == null || tmp2 == null){
                 break;
             }
        }

         while(tmp1 != null){
                 returnList.insertLast(tmp1.element);
                 tmp1 = tmp1.succ;
                 iteration--;
             }

         while(tmp2 != null){
                 returnList.insertLast(tmp2.element);
                 tmp2 = tmp2.succ;
                 iteration--;
             }
        return (returnList);
    }
    public static void main(String[] args) {
        //Scanner input = new Scanner(System.in);

        //int n = input.nextInt();

        SLL<Integer> list1 = new SLL<>();
        for(int i=0;i<8;i++) {
            list1.insertLast(i+1);
        }

        //n = input.nextInt();

        SLL<Integer> list2 = new SLL<>();
        for(int i=0;i<3;i++) {
            list2.insertLast(i+1);
        }

        SpecialSLLJoin<Integer> tmp = new SpecialSLLJoin<>();

        System.out.println(tmp.specialJoin(list1, list2));
    }

}
