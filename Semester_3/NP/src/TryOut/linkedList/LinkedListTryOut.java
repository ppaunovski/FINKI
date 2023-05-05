package TryOut.linkedList;

import lab5.GenericQueue.Node;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LinkedListTryOut {
    public static void main(String[] args) {
        LinkedList<Node<Integer>> list = new LinkedList<>();
        Node<Integer> tmp = new Node<>(null, null);
        list.add(new Node<>(0, null));
        for(int i=0; i<6; i++){
            list.add(new Node<>(i, list.getFirst()));
        }

        tmp = list.getFirst();
        Iterator<Node<Integer>> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
