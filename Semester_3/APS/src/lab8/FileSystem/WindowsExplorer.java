package lab8.FileSystem;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.NoSuchElementException;

interface Tree<E> {
    ////////////Accessors ////////////

    public Node<E> root();

    public Node<E> parent(Node<E> node);

    public int childCount(Node<E> node);

    ////////////Transformers ////////////
    public void makeRoot(E elem);

    public Node<E> addChild(Node<E> node, E elem);

    public void remove(Node<E> node);

    ////////////Iterator ////////////
    public Iterator<E> children(Node<E> node);

}

interface Node<E> {

    public E getElement();

    public void setElement(E elem);
}


class SLLTree<E extends Comparable<E>> implements Tree<E> {

    public SLLNode<E> root;

    public SLLTree() {
        root = null;
    }

    public Node<E> root() {
        return root;
    }

    public Node<E> parent(Node<E> node) {
        return ((SLLNode<E>) node).parent;
    }

    public int childCount(Node<E> node) {
        SLLNode<E> tmp = ((SLLNode<E>) node).firstChild;
        int num = 0;
        while (tmp != null) {
            tmp = tmp.sibling;
            num++;
        }
        return num;
    }

    public void makeRoot(E elem) {
        root = new SLLNode<E>(elem);
    }

    public Node<E> addChild(Node<E> node, E elem) {
        SLLNode<E> tmp = new SLLNode<E>(elem);
        SLLNode<E> curr = (SLLNode<E>) node;
        tmp.sibling = curr.firstChild;
        curr.firstChild = tmp;
        tmp.parent = curr;
        return tmp;
    }

    public Node<E> addChildLexicographically(Node<E> node, E elem){
        SLLNode<E> newChild = new SLLNode<>(elem);
        SLLNode<E> curr = (SLLNode<E>) node;
        if(curr.firstChild == null){
            return this.addChild(curr, elem);
        }
        SLLNode<E> tmpChild = curr.firstChild;
        SLLNode<E> prevChild = tmpChild;
        boolean wasAdded = false;
        while (tmpChild != null){
            if(tmpChild.getElement().compareTo(elem) > 0){
                if(tmpChild == prevChild){
                    curr.firstChild = newChild;
                }
                else {
                    prevChild.sibling = newChild;
                }
                newChild.sibling = tmpChild;
                wasAdded = true;
                break;
            }
            prevChild = tmpChild;
            tmpChild = tmpChild.sibling;
        }
        if(!wasAdded){
            prevChild.sibling = newChild;
        }
        newChild.parent = curr;
        return newChild;
    }

    public void remove(Node<E> node) {
        SLLNode<E> curr = (SLLNode<E>) node;
        if (curr.parent != null) {
            if (curr.parent.firstChild == curr) {
                // The node is the first child of its parent
                // Reconnect the parent to the next sibling
                curr.parent.firstChild = curr.sibling;
            } else {
                // The node is not the first child of its parent
                // Start from the first and search the node in the sibling list and remove it
                SLLNode<E> tmp = curr.parent.firstChild;
                while (tmp.sibling != curr) {
                    tmp = tmp.sibling;
                }
                tmp.sibling = curr.sibling;
            }
        } else {
            root = null;
        }
    }

    public void path(Node<E> tekoven) {
        recursivePath((SLLNode<E>) tekoven);
        System.out.println();
    }

    private void recursivePath(SLLNode<E> node) {
        if(node != null){
            recursivePath(node.parent);
            System.out.print(node.getElement() + "\\");
        }


    }

    class SLLTreeIterator<T extends Comparable<T>> implements Iterator<T> {

        SLLNode<T> start, current;

        public SLLTreeIterator(SLLNode<T> node) {
            start = node;
            current = node;
        }

        public boolean hasNext() {
            return (current != null);
        }

        public T next() throws NoSuchElementException {
            if (current != null) {
                SLLNode<T> tmp = current;
                current = current.sibling;
                return tmp.getElement();
            } else {
                throw new NoSuchElementException();
            }
        }

        public void remove() {
            if (current != null) {
                current = current.sibling;
            }
        }
    }

    public Iterator<E> children(Node<E> node) {
        return new SLLTreeIterator<E>(((SLLNode<E>) node).firstChild);
    }

    void printTreeRecursive(Node<E> node, int level) {
        if (node == null)
            return;
        int i;
        SLLNode<E> tmp;

        for (i=0;i<level;i++)
            System.out.print(" ");
        System.out.println(node.getElement().toString());
        tmp = ((SLLNode<E>)node).firstChild;
        while (tmp != null) {
            printTreeRecursive(tmp, level+1);
            tmp = tmp.sibling;
        }
    }

    public void printTree() {
        printTreeRecursive(root, 0);
    }

    public Node<E> open(Node<E> tekoven, String find){
//        if(root.getElement().equals(find)){
//            return root;
//        }

        SLLNode<E> current = (SLLNode<E>) tekoven;

        return openRecursive(current, find);
    }

    SLLNode<E> openRecursive(SLLNode<E> node, String find){
        if(node == null)
            return null;
        if(node.getElement().equals(find)){
            return node;
        }
        SLLNode<E> tmp = node;
        while (tmp != null){
            if(tmp.getElement().equals(find))
                return tmp;


            tmp = tmp.sibling;
        }
        tmp = node;
        while (tmp != null){
            SLLNode<E> found = openRecursive(tmp.firstChild, find);
            if(found != null){
                return found;
            }
            tmp = tmp.sibling;
        }
        return null;
    }

}

class SLLNode<P extends Comparable<P>> implements Node<P> {

    // Holds the links to the needed nodes
    public SLLNode<P> parent, sibling, firstChild;
    // Hold the data
    public P element;

    public SLLNode(P o) {
        element = o;
        parent = sibling = firstChild = null;
    }

    public P getElement() {
        return element;
    }

    public void setElement(P o) {
        element = o;
    }
}

public class WindowsExplorer {

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String commands[] = new String[N];

        for (i=0;i<N;i++)
            commands[i] = br.readLine();

        br.close();

        SLLTree<String> tree = new SLLTree<String>();
        tree.makeRoot("c:");

        // vasiot kod stoi ovde

        Node<String> tekoven = tree.root();

        for(String command : commands){
            String []parts = command.split("\\s+");

            if(parts.length == 2){
                //Create , open delete
                switch (parts[0]){
                    case "CREATE": tree.addChildLexicographically(tekoven, parts[1]); break;
                    case "OPEN": tekoven = tree.open(tekoven, parts[1]); break;
                    case "DELETE": tree.remove(tree.open(tekoven, parts[1])); break;
                }
            }
            else {
                //path back print
                switch (parts[0]){
                    case "PRINT": tree.printTree(); break;
                    case "PATH": tree.path(tekoven); break;
                    case "BACK": tekoven = tree.parent(tekoven); break;
                }
            }
        }

    }



//    private static void addFolderLexicographically(SLLTree<String> tree, Node<String> parent, String child) {
//        Iterator<String> children = tree.children(parent);
//        String tmp = null;
//        while (children.hasNext()){
//            tmp = children.next();
//            if(tmp != null && tmp.compareTo(child) > 0)
//                break;
//        }
//
//        if(tmp != null){
//
//        }
//    }

}
