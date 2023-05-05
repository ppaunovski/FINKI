package lab5.GenericQueue;

import java.util.List;

public class Queue<T> {
    Node<T> head;
    Node<T> tale;

    public Queue() {
        head = null;
        tale = null;
    }

    public boolean isEmpty(){
        return head == null;
    }

    public void enqueue(T element){
        Node<T> node = new Node<>(element, null);
        if(head == null){
            tale = node;
            head = node;
            return;
        }
        if(head == tale){
            tale = node;
            head.setNext(tale);
            return;
        }
        tale.setNext(node);
        tale = tale.getNext();
    }

    public T dequeue() throws EmptyQueueException{
        if(head == null) throw new EmptyQueueException();
        Node<T> returnNode = head;
        if(head == tale){
            tale = tale.getNext();
        }
        head = head.getNext();
        return returnNode.getElement();
    }

    public T peek() throws EmptyQueueException{
        if(head == null) throw new EmptyQueueException();
        return head.getElement();
    }

    public T inspect() throws EmptyQueueException{
        if(tale == null) throw new EmptyQueueException();
        return tale.getElement();
    }

    public int count(){
        Node<T> tmp = head;
        int count = 0;
        while(tmp != null){
            count++;
            tmp = tmp.getNext();
        }
        return count;
    }
}
