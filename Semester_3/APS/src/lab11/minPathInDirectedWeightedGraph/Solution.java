package lab11.minPathInDirectedWeightedGraph;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


interface Queue<E> {

    // Elementi na redicata se objekti od proizvolen tip.

    // Metodi za pristap:

    public boolean isEmpty ();
    // Vrakja true ako i samo ako redicata e prazena.

    public int size ();
    // Ja vrakja dolzinata na redicata.

    public E peek ();
    // Go vrakja elementot na vrvot t.e. pocetokot od redicata.

    // Metodi za transformacija:

    public void clear ();
    // Ja prazni redicata.

    public void enqueue (E x);
    // Go dodava x na kraj od redicata.

    public E dequeue ();
    // Go otstranuva i vrakja pochetniot element na redicata.

}

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

class GraphNode<E> {
    private int index; //index (reden broj) na temeto vo grafot
    private E info;
    private LinkedList<GraphNodeNeighbor<E>> neighbors;

    public GraphNode(int index, E info) {
        this.index = index;
        this.info = info;
        neighbors = new LinkedList<GraphNodeNeighbor<E>>();
    }

    public boolean containsNeighbor(GraphNode<E> o){
        GraphNodeNeighbor<E> pom = new GraphNodeNeighbor<E>(o,0);
        return neighbors.contains(pom);
    }

    public void addNeighbor(GraphNode<E> o, float weight){
        GraphNodeNeighbor<E> pom = new GraphNodeNeighbor<E>(o,weight);
        neighbors.add(pom);
    }

    public void removeNeighbor(GraphNode<E> o){
        GraphNodeNeighbor<E> pom = new GraphNodeNeighbor<E>(o,0);
        if(neighbors.contains(pom))
            neighbors.remove(pom);
    }

    @Override
    public String toString() {
        String ret= "INFO:"+info+" SOSEDI:";
        for(int i=0;i<neighbors.size();i++)
            ret+="("+neighbors.get(i).node.info+","+neighbors.get(i).weight+") ";
        return ret;

    }

    public void updateNeighborWeight(GraphNode<E> o, float weight){
        Iterator<GraphNodeNeighbor<E>> i = neighbors.iterator();
        while(i.hasNext()){
            GraphNodeNeighbor<E> pom = i.next();
            if(pom.node.equals(o))
                pom.weight = weight;
        }

    }

    @Override
    public boolean equals(Object obj) {
        @SuppressWarnings("unchecked")
        GraphNode<E> pom = (GraphNode<E>)obj;
        return (pom.info.equals(this.info));
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public E getInfo() {
        return info;
    }

    public void setInfo(E info) {
        this.info = info;
    }

    public LinkedList<GraphNodeNeighbor<E>> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(LinkedList<GraphNodeNeighbor<E>> neighbors) {
        this.neighbors = neighbors;
    }



}

class GraphNodeNeighbor<E> {
    GraphNode<E> node;
    float weight;

    public GraphNodeNeighbor(GraphNode<E> node, float weight) {
        this.node = node;
        this.weight = weight;
    }

    public GraphNodeNeighbor(GraphNode<E> node) {
        this.node = node;
        this.weight = 0;
    }

    @Override
    public boolean equals(Object obj) {
        @SuppressWarnings("unchecked")
        GraphNodeNeighbor<E> pom = (GraphNodeNeighbor<E>)obj;
        return pom.node.equals(this.node);
    }





}

class LinkedQueue<E> implements Queue<E> {

    // Redicata e pretstavena na sledniot nacin:
    // length go sodrzi brojot na elementi.
    // Elementite se zachuvuvaat vo jazli dod SLL
    // front i rear se linkovi do prviot i posledniot jazel soodvetno.
    SLLNode<E> front, rear;
    int length;

    // Konstruktor ...

    public LinkedQueue () {
        clear();
    }

    public boolean isEmpty () {
        // Vrakja true ako i samo ako redicata e prazena.
        return (length == 0);
    }

    public int size () {
        // Ja vrakja dolzinata na redicata.
        return length;
    }

    public E peek () {
        // Go vrakja elementot na vrvot t.e. pocetokot od redicata.
        if (front == null)
            throw new NoSuchElementException();
        return front.element;
    }

    public void clear () {
        // Ja prazni redicata.
        front = rear = null;
        length = 0;
    }

    public void enqueue (E x) {
        // Go dodava x na kraj od redicata.
        SLLNode<E> latest = new SLLNode<E>(x, null);
        if (rear != null) {
            rear.succ = latest;
            rear = latest;
        } else
            front = rear = latest;
        length++;
    }

    public E dequeue () {
        // Go otstranuva i vrakja pochetniot element na redicata.
        if (front != null) {
            E frontmost = front.element;
            front = front.succ;
            if (front == null)  rear = null;
            length--;
            return frontmost;
        } else
            throw new NoSuchElementException();
    }

}




class Graph<E> {

    int num_nodes;
    GraphNode<E> adjList[];

    @SuppressWarnings("unchecked")
    public Graph(int num_nodes, E[] list) {
        this.num_nodes = num_nodes;
        adjList = (GraphNode<E>[]) new GraphNode[num_nodes] ;
        for(int i=0;i<num_nodes;i++)
            adjList[i] = new GraphNode<E>(i,list[i]);
    }



    @SuppressWarnings("unchecked")
    public Graph(int num_nodes) {
        this.num_nodes = num_nodes;
        adjList = (GraphNode<E>[]) new GraphNode[num_nodes] ;
    }



    int adjacent(int x, int y) {
        // proveruva dali ima vrska od jazelot so
        // indeks x do jazelot so indeks y
        return (adjList[x].containsNeighbor(adjList[y]))?1:0;
    }

    void addEdge(int x,int y,float tezina){
        // dodava vrska od jazelot so indeks x do jazelot so indeks y so tezina
        if(adjList[x].containsNeighbor(adjList[y])){
            adjList[x].updateNeighborWeight(adjList[y], tezina);
        }
        else
            adjList[x].addNeighbor(adjList[y], tezina);
    }

    void deleteEdge(int x,int y){
        adjList[x].removeNeighbor(adjList[y]);
    }

    void dfsSearch(int node) {
        boolean visited[] = new boolean[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            visited[i] = false;
        dfsRecursive(node, visited);
    }

    void dfsRecursive(int node, boolean visited[]) {
        visited[node] = true;
        System.out.println(node + ": " + adjList[node].getInfo());

        for (int i = 0; i < adjList[node].getNeighbors().size(); i++) {
            GraphNode<E> pom = adjList[node].getNeighbors().get(i).node;
            if (!visited[pom.getIndex()])
                dfsRecursive(pom.getIndex(), visited);
        }
    }

    void dfsNonrecursive(int node) {
        boolean visited[] = new boolean[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            visited[i] = false;
        visited[node] = true;
        System.out.println(node+": " + adjList[node].getInfo());
        Stack<Integer> s = new Stack<Integer>();
        s.push(node);

        GraphNode<E> pom;

        while (!s.isEmpty()) {
            pom = adjList[s.peek()];
            GraphNode<E> tmp=null;
            for (int i = 0; i < pom.getNeighbors().size(); i++) {
                tmp = pom.getNeighbors().get(i).node;
                if (!visited[tmp.getIndex()])
                    break;
            }
            if(tmp!=null && !visited[tmp.getIndex()]){
                visited[tmp.getIndex()] = true;
                System.out.println(tmp.getIndex() + ": " + tmp.getInfo());
                s.push(tmp.getIndex());
            }
            else
                s.pop();
        }

    }

    void bfs(int node){
        boolean visited[] = new boolean[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            visited[i] = false;
        visited[node] = true;
        System.out.println(node+": " + adjList[node].getInfo());
        Queue<Integer> q = new LinkedQueue<Integer>();
        q.enqueue(node);

        GraphNode<E> pom;

        while(!q.isEmpty()){
            pom = adjList[q.dequeue()];
            GraphNode<E> tmp=null;
            for (int i = 0; i < pom.getNeighbors().size(); i++) {
                tmp = pom.getNeighbors().get(i).node;
                if (!visited[tmp.getIndex()]){
                    visited[tmp.getIndex()] = true;
                    System.out.println(tmp.getIndex()+": " + tmp.getInfo());
                    q.enqueue(tmp.getIndex());
                }
            }


        }

    }

    int getIndexOfNodeWithInfo(E info){
        for (GraphNode<E> eGraphNode : adjList) {
            if(eGraphNode.getInfo().equals(info)){
                return eGraphNode.getIndex();
            }
        }
        return -1;
    }


    @Override
    public String toString() {
        String ret = new String();
        for(int i=0;i<this.num_nodes;i++)
            ret+=adjList[i]+"\n";
        return ret;
    }

    int searchIndex(String key)
    {
        for(int i=0;i<num_nodes;i++){
            Iterator<GraphNodeNeighbor<E>> it = adjList[i].getNeighbors().iterator();
            while (it.hasNext()) {
                GraphNodeNeighbor<E> pom = it.next();
                if(pom.node.getInfo().equals(key))
                    return pom.node.getIndex();

            }
        }
        return -1;
    }

    /***************** DIJKSTRA ******************************************************************************/

    float[] dijkstra(int from, int []prethodnik) {

        /* Minimalna cena do sekoj od teminjata */
        float distance[] = new float[this.num_nodes];
        /* dali za temeto e najdena konecnata (minimalna) cena */
        boolean finalno[] = new boolean[this.num_nodes];
        for (int i = 0; i < this.num_nodes; i++) {
            distance[i] = -1;
            finalno[i] = false;
        }

        finalno[from] = true;
        distance[from] = 0;

        /*
         * vo sekoj cekor za edno teme se dobiva konecna minimalna cena
         */
        for (int i = 1; i < this.num_nodes; i++) {
            /* za site sledbenici na from presmetaj ja cenata */
            Iterator<GraphNodeNeighbor<E>> it = adjList[from].getNeighbors()
                    .iterator();
            while (it.hasNext()) {
                GraphNodeNeighbor<E> pom = it.next();
                /* ako grankata kon sosedot nema konecna cena */
                if (!finalno[pom.node.getIndex()]) {
                    /* ako ne e presmetana cena za temeto */
                    if (distance[pom.node.getIndex()] <= 0) {
                        distance[pom.node.getIndex()] = distance[from]
                                + pom.weight;
                        prethodnik[pom.node.getIndex()] = from;
                    }
                    /* inaku, ako e pronajdena poniska */
                    else if (distance[from] + pom.weight < distance[pom.node
                            .getIndex()]) {
                        distance[pom.node.getIndex()] = distance[from]
                                + pom.weight;
                        prethodnik[pom.node.getIndex()] = from;
                    }
                }
            }

            /* najdi teme so min. cena koja ne e konecna */
            boolean minit = false; /* min. ne e inicijaliziran */
            int k = 0;
            float minc = -1;
            /* proveri gi site teminja */
            for (int j = 0; j < this.num_nodes; j++) {
                if (!finalno[j] && distance[j] != -1) { /*ako cenata ne e  konecna*/
                    if (!minit) { /* ako ne e inicijaliziran minimumot */
                        minc = distance[k = j]; /* proglasi go ova e minimum */
                        minit = true; /* oznaci deka min e inicijaliziran */
                    }
                    /* proveri dali e pronajdeno teme so pomala cena */
                    else if (minc > distance[j] && distance[j] > 0)
                        minc = distance[k = j];
                }
            }
            finalno[from = k] = true;
        }

        return distance;

    }

    /*******************************************************************************************************/


    public float getMinDist(int from, int to){
        int []prethodnik = new int[num_nodes];

        float []fromTo = dijkstra(from, prethodnik);
        Stack<Integer> stack = new Stack<>();

        for(int i=to; i!=from; i = prethodnik[i]){
            stack.push(i);
        }
        stack.push(from);

        while (!stack.isEmpty()){
            System.out.print(adjList[stack.pop()].getInfo() + " ");
        }
        System.out.println();

        prethodnik = new int[num_nodes];

        float []toFrom = dijkstra(to, prethodnik);

        for(int i=from; i!=to; i = prethodnik[i]){
            stack.push(i);
        }
        stack.push(to);

        while (!stack.isEmpty()){
            System.out.print(adjList[stack.pop()].getInfo() + " ");
        }
        System.out.println();
        return fromTo[to] + toFrom[from];
    }

}

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        String []gradovi = new String[n];

        Graph<String> graph = new Graph<>(n, gradovi);

        int numEdges = Integer.parseInt(sc.nextLine());

        for(int i=0; i<numEdges; i++){
            String line = sc.nextLine();
            String [] parts = line.split("\\s+");

            int indexStartCity = Integer.parseInt(parts[0]);
            int indexEndCity = Integer.parseInt(parts[2]);

            String startCity = parts[1];
            String endCIty = parts[3];

            int length = Integer.parseInt(parts[4]);
//            if(graph.adjList[indexStartCity].getInfo() == (null)){
//                graph.adjList[indexStartCity] = new GraphNode<>(indexStartCity, startCity);
//            }
//            if(graph.adjList[indexEndCity].getInfo() == (null)){
//                graph.adjList[indexEndCity] = new GraphNode<>(indexEndCity, endCIty);
//            }
            graph.adjList[indexStartCity].setInfo(startCity);
            graph.adjList[indexEndCity].setInfo(endCIty);
            graph.addEdge(indexStartCity, indexEndCity, length);
        }
        String f = sc.nextLine();
        String t = sc.nextLine();
        int from = graph.getIndexOfNodeWithInfo(f);
        int to = graph.getIndexOfNodeWithInfo(t);
        float roundTrip ;
        if(from != -1 && to != -1){
            roundTrip = graph.getMinDist(from, to);
        }
        else
            roundTrip = 0;

        System.out.println(roundTrip);
    }


}
