//package lab10;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.*;
//
//class Graph<E> {
//
//    int num_nodes;
//    int [][]matrixAdj;
//    Map<Integer, GraphNode<Character>> nodes;
//
//    @SuppressWarnings("unchecked")
//    public Graph() {
//        num_nodes = 0;
//        matrixAdj = new int[0][0];
//        nodes = new HashMap<>();
//    }
//
//    void create(int num_nodes){
//        this.num_nodes = num_nodes;
//        this.matrixAdj = new int[num_nodes][num_nodes];
//
//        for(int i=0; i<num_nodes; i++){
//            nodes.put(i, new GraphNode<>(i, (char)('A'+i)));
//        }
//    }
//
//    int adjacent(int x, int y) {
//        // proveruva dali ima vrska od jazelot so
//        // indeks x do jazelot so indeks y
//        return matrixAdj[x][y];
//    }
//
//    void addEdge(int x, int y) {
//        // dodava vrska od jazelot so indeks x do jazelot so indeks y
//        if(x < num_nodes && y < num_nodes){
//            matrixAdj[x][y] = 1;
//            matrixAdj[y][x] = 1;
//
//            nodes.get(x).addNeighbor(nodes.get(y));
//            nodes.get(y).addNeighbor(nodes.get(x));
//        }
//    }
//
//    void deleteEdge(int x, int y) {
//        matrixAdj[x][y] = 0;
//        matrixAdj[y][x] = 0;
//
//        nodes.get(x).removeNeighbor(nodes.get(y));
//        nodes.get(y).removeNeighbor(nodes.get(x));
//    }
//
//    void printNode(int x){
//        System.out.println(nodes.get(x).getInfo());
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//
//        for(int i=0; i<num_nodes; i++){
//            for(int j=0; j<num_nodes; j++){
//                sb.append(matrixAdj[i][j]);
//                if(j+1 != num_nodes){
//                    sb.append(" ");
//                }
//            }
//            if(i+1 != num_nodes){
//                sb.append("\n");
//            }
//        }
//        return sb.toString();
//    }
//
//}
//
//class GraphNode<E> {
//    private int index;//index (reden broj) na temeto vo grafot
//    private E info;
//    private LinkedList<GraphNode<E>> neighbors;
//
//    public GraphNode(int index, E info) {
//        this.index = index;
//        this.info = info;
//        neighbors = new LinkedList<GraphNode<E>>();
//    }
//
//    boolean containsNeighbor(GraphNode<E> o){
//        return neighbors.contains(o);
//    }
//
//    void addNeighbor(GraphNode<E> o){
//        neighbors.add(o);
//    }
//
//    void removeNeighbor(GraphNode<E> o){
//        if(neighbors.contains(o))
//            neighbors.remove(o);
//    }
//
//    @Override
//    public String toString() {
//        String ret= "INFO:"+info+" SOSEDI:";
//        for(int i=0;i<neighbors.size();i++)
//            ret+=neighbors.get(i).info+" ";
//        return ret;
//
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        @SuppressWarnings("unchecked")
//        GraphNode<E> pom = (GraphNode<E>)obj;
//        return (pom.info.equals(this.info));
//    }
//
//    public int getIndex() {
//        return index;
//    }
//
//    public void setIndex(int index) {
//        this.index = index;
//    }
//
//    public E getInfo() {
//        return info;
//    }
//
//    public void setInfo(E info) {
//        this.info = info;
//    }
//
//    public LinkedList<GraphNode<E>> getNeighbors() {
//        return neighbors;
//    }
//
//    public void setNeighbors(LinkedList<GraphNode<E>> neighbors) {
//        this.neighbors = neighbors;
//    }
//
//}
//
//public class UndirectedUnweightedGraphMatrixOfAdj {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        int n = Integer.parseInt(br.readLine());
//        Graph<Character> graph = new Graph<>();
//
//        for(int i=0; i<n; i++){
//            String line = br.readLine();
//            String [] parts = line.split("\\s+");
//
//            switch (parts[0]){
//                case "CREATE":
//                    graph.create(Integer.parseInt(parts[1]));
//                    break;
//                case "ADDEDGE":
//                    graph.addEdge(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
//                    break;
//                case "DELETEEDGE":
//                    graph.deleteEdge(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
//                    break;
//                case "ADJACENT":
//                    System.out.println(graph.adjacent(Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
//                    break;
//                case "PRINTMATRIX":
//                    System.out.println(graph);
//                    break;
//                case "PRINTNODE":
//                    graph.printNode(Integer.parseInt(parts[1]));
//                    break;
//            }
//        }
//    }
//}
