package VEZBI.Listi.ForumSLL;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

    void setFirst(SLLNode<E> set){
        first = set;
    }
}

class Discussion{
    int id;
    int popularity;
    int users;

    public Discussion(int id, int popularity, int users) {
        this.id = id;
        this.popularity = popularity;
        this.users = users;
    }

    int importance(){
        return users*popularity;
    }

    @Override
    public String toString() {
        return String.format("%d", id);
    }
}


public class ForumSLL {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int healthN = Integer.parseInt(br.readLine());
        int  financeN = Integer.parseInt(br.readLine());

        SLL<Discussion> health = new SLL<>();
        SLL<Discussion> finance = new SLL<>();
        SLLNode<Discussion> tmp;
        for(int i=0; i<healthN; i++){
            String line = br.readLine();
            String []parts = line.split("\\s+");
            health.insertLast(new Discussion(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
        }

        for(int i=0; i<financeN; i++){
            String line = br.readLine();
            String []parts = line.split("\\s+");
            finance.insertLast(new Discussion(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
        }

        forum(health, finance);
    }

    static void forum(SLL<Discussion> health, SLL<Discussion> finance){
        SLLNode<Discussion> tmp = finance.getFirst();
        SLLNode<Discussion> prev = null;
        SLLNode<Discussion> min = null;
        int minImp = Integer.MAX_VALUE;
        while (tmp != null){
            if(minImp > tmp.element.importance()){
                minImp = tmp.element.importance();
                min = tmp;
            }
            tmp = tmp.succ;
        }
        tmp = finance.getFirst();
        boolean isFirst = true;

        while (tmp != null ){
            if(isFirst){
                if(tmp == min){
                    finance.setFirst(tmp.succ);
                }
                isFirst = false;
            }
            else {
                if(tmp == min){
                    prev.succ = tmp.succ;
                }
            }
            prev = tmp;
            tmp = tmp.succ;
        }

        tmp = health.getFirst();
        int maxImp = 0;
        SLLNode<Discussion> maxHealth = null;

        while (tmp != null){
            if(maxImp < tmp.element.importance()){
                maxHealth = tmp;
                maxImp = tmp.element.importance();
            }
            tmp = tmp.succ;
        }

        if(maxHealth != null){
            finance.insertLast(new Discussion(maxHealth.element.id, maxHealth.element.popularity, maxHealth.element.users));

        }

        System.out.println(health);
        System.out.println(finance);
    }
}
