package SecondTimePackage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashSet;

public class Graph {
    public class GraphNode {
        public int data;
        ArrayList<GraphNode> neighbors;
    
        public GraphNode(int data) {
            this.data = data;
            this.neighbors = new ArrayList<>();
        }

    
        public void addNieghbor(GraphNode node) {
            neighbors.add(node);
            node.neighbors.add(this);
        }
    }
    
    public class GraphContainer {
        ArrayList<GraphNode> nodes;
        int size;
        
        public GraphContainer() {
            size = 0;
            nodes = new ArrayList<>();
        }
    
        public void addNode(GraphNode node) {
            nodes.add(node);
            size++;
        }  

        public int size() {
            return size;
        }
    }
    
    
    public boolean doesRouteExist(GraphContainer graph, GraphNode start, GraphNode end) {
        // boolean[] visited = new boolean[graph.size + 1];
        // return doesRouteExistDFS(start, end, visited);

        boolean[] visited = new boolean[graph.size + 1];
        LinkedList<GraphNode> queue = new LinkedList<>();
        queue.add(start);
        visited[start.data] = true;

        while (!queue.isEmpty()) {
            GraphNode node = queue.removeFirst();

            for (GraphNode n : node.neighbors) {
                if (n.data == end.data) {
                    return true;
                }

                if (!visited[n.data]) {
                    visited[n.data] = true;
                    queue.addLast(n);
                }
            }
        }

        return false;
    }
    
    private boolean doesRouteExistDFS(GraphNode node, GraphNode dest, boolean[] visited) {
        // base case
        if (node.data == dest.data) {
            return true;
        }
        
        // recursive phase
        for (GraphNode n : node.neighbors) {
            if (!visited[n.data]) {
                visited[n.data] = true;    // mark it visited to avoid revisiting
                if (doesRouteExistDFS(n, dest, visited)) {
                    return true;
                }
            }
        }
        
        // when reached a leaf node
        return false;
    }

    public ArrayList<LinkedList<GraphNode>> layers(GraphContainer graph, GraphNode root) {
        ArrayList<LinkedList<GraphNode>> result = new ArrayList<>();

        LinkedList<GraphNode> currLevel = new LinkedList<>();
        LinkedList<GraphNode> nextLevel = new LinkedList<>();
        currLevel.add(root);
        result.add((LinkedList)currLevel.clone());

        HashSet<GraphNode> visited = new HashSet<>();
        visited.add(root);

        while (!currLevel.isEmpty()) {
            // look through all nodes at a current level
            while (!currLevel.isEmpty()) {
                GraphNode currNode = currLevel.removeFirst();
                // add neighbors of a node in a current level
                for (GraphNode adj : currNode.neighbors) {
                    if (!visited.contains(adj)) {
                        nextLevel.add(adj);
                        visited.add(adj);   // mark visited
                    }
                }
            }
            // adds a layer to a result list
            if (!nextLevel.isEmpty()) {
                result.add((LinkedList)nextLevel.clone());
            }
            currLevel = nextLevel;
            nextLevel = new LinkedList<>();
        }

        return result;
    }
    
}
