/*
    Problem Link: https://www.geeksforgeeks.org/depth-first-search-or-dfs-for-a-graph/
    
    DFS (Depth First Search) Traversal of Graph
*/

import java.util.*;

class Graph {
    public Map<Integer, Boolean> visited;
    public Map<Integer, List<Integer>> adj;

    public Graph() {
        visited = new HashMap<>();
        adj = new HashMap<>();
    }

    // function to add an edge to graph
    public void addEdge(int v, int w) {
        adj.computeIfAbsent(v, k -> new ArrayList<>()).add(w); // Add w to v's list.
    }

    // DFS traversal of the vertices
    // reachable from v
    public void DFS(int v) {
        // Mark the current node as visited and
        // print it
        visited.put(v, true);
        System.out.print(v + " ");

        // Recur for all the vertices adjacent
        // to this vertex
        List<Integer> neighbors = adj.getOrDefault(v, new ArrayList<>());
        for (int i : neighbors) {
            if (!visited.getOrDefault(i, false)) {
                DFS(i);
            }
        }
    }
}

class DFS {
    public static void main(String[] args) {
        // Create a graph given in the above diagram
        Graph g = new Graph();
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("DFS is (starting from vertex 2)");
        g.DFS(2);
    }
}
