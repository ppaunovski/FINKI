package lab8.BinaryTreeSum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

class BNode<E> {

    public E info;
    public BNode<E> left;
    public BNode<E> right;

    static int LEFT = 1;
    static int RIGHT = 2;

    public BNode(E info) {
        this.info = info;
        left = null;
        right = null;
    }

    public BNode() {
        this.info = null;
        left = null;
        right = null;
    }

    public BNode(E info, BNode<E> left, BNode<E> right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }

}

class BTree<E extends Comparable<E>> {

    public BNode<E> root;

    public BTree() {
        root = null;
    }

    public BTree(E info) {
        root = new BNode<E>(info);
    }

    public void makeRoot(E elem) {
        root = new BNode(elem);
    }

    public void makeRootNode(BNode<E> node) {
        root = node;
    }

    public BNode<E> addChild(BNode<E> node, int where, E elem) {

        BNode<E> tmp = new BNode<E>(elem);

        if (where == BNode.LEFT) {
            if (node.left != null)  // veke postoi element
                return null;
            node.left = tmp;
        } else {
            if (node.right != null) // veke postoi element
                return null;
            node.right = tmp;
        }

        return tmp;
    }

    public BNode<E> addChildNode(BNode<E> node, int where, BNode<E> tmp) {

        if (where == BNode.LEFT) {
            if (node.left != null)  // veke postoi element
                return null;
            node.left = tmp;
        } else {
            if (node.right != null) // veke postoi element
                return null;
            node.right = tmp;
        }

        return tmp;
    }

    public void preorderPrint(){
        System.out.println(root.info);
        recursivePrint(root.left, root.right);
    }

    private void recursivePrint(BNode<E> left, BNode<E> right) {
        if(left == null && right == null){
            return;
        }
        if(left != null){
            System.out.println(left.info);
            recursivePrint(left.left, left.right);
        }
        if(right != null){
            System.out.println(right.info);
            recursivePrint(right.left, right.right);
        }


    }

    public BNode<E> findNode(E i) {
        if(root.info == i)
            return root;
        return findNodeRecursive(root.left, root.right, i);
    }

    private BNode<E> findNodeRecursive(BNode<E> left, BNode<E> right, E i) {
        if(left == null && right == null)
            return null;
        if(left != null){
            if(left.info == i)
                return left;
            else
                return findNodeRecursive(left.left, left.right, i);
        }
        if(right.info == i)
            return right;
        else
            return findNodeRecursive(right.left, right.right, i);
    }

    public Integer lesserSum(BNode<Integer> left, BNode<Integer> from){
        if(left == null){
            return 0;
        }
        else {
            if(left.info < from.info){
                return lesserSum(left.left, from) + lesserSum(left.right, from) + left.info;
            }
            return lesserSum(left.left, from) + lesserSum(left.right, from);
        }
    }
@SuppressWarnings("unchecked")
    public Integer greaterSum(BNode<Integer> right, BNode<Integer> from){
        if(right == null){
            return 0;
        }
        else {
            if(right.info > from.info){
                return lesserSum(right.left, from) + lesserSum(right.right, from) + right.info;
            }
            return lesserSum(right.left, from) + lesserSum(right.right, from);
        }
    }

}

public class BinaryTreeSum {


    public static void main(String[] args) throws Exception {
        int i, j, k;
        int index;
        String action;

        String line;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        BNode<Integer> nodes[] = new BNode[N];
        BTree<Integer> tree = new BTree<Integer>();

        for (i=0;i<N;i++)
            nodes[i] = new BNode<Integer>();

        for (i = 0; i < N; i++) {
            line = br.readLine();
            st = new StringTokenizer(line);
            index = Integer.parseInt(st.nextToken());
            nodes[index].info = Integer.parseInt(st.nextToken());
            action = st.nextToken();
            if (action.equals("LEFT")) {
                tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNode.LEFT, nodes[index]);
            } else if (action.equals("RIGHT")) {
                tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNode.RIGHT, nodes[index]);
            } else {
                // this node is the root
                tree.makeRootNode(nodes[index]);
            }
        }

       int baranaVrednost=Integer.parseInt(br.readLine());

        br.close();

        // vasiot kod ovde
        //tree.preorderPrint();

        BNode<Integer> from = new BNode();
        for (i = 0; i < nodes.length; i++) {
            if (nodes[i].info == baranaVrednost){
                from = nodes[i];
            }
        }

        //tree.makeRootNode(newRoot);

       // tree.preorderPrint();

        System.out.println(tree.lesserSum(from.left, from) + " " + tree.greaterSum(from.right, from));
        List<Integer> list = new ArrayList<>();


    }
}

