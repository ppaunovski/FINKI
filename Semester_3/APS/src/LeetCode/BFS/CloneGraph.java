package LeetCode.BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CloneGraph {
}


// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}


class Solution {
    public Node cloneGraph(Node node) {
        if(node == null){
            return null;
        }
        Node copy = new Node(node.val);
        Node []visited = new Node[101];

        dfs(node, copy, visited);

        return copy;
    }

    private void dfs(Node node, Node copy, Node[] visited) {
        visited[copy.val] = copy;
        for(Node neighbour : node.neighbors){
            if(visited[neighbour.val] == null){
                Node newNode = new Node(neighbour.val);
                copy.neighbors.add(newNode);
                dfs(neighbour, newNode, visited);
            }
            else {
                copy.neighbors.add(visited[neighbour.val]);
            }
        }
    }
}
