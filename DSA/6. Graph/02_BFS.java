/*
    Problem Link: https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/
    
    BFS (Breadth First Search) Traversal of Graph
*/

import java.util.*;

class Graph {
    public int V; // No. of vertices
    public List<Integer>[] adj;

    public Graph(int V) {
        this.V = V;
        adj = new ArrayList[100];
        for (int i = 0; i < 100; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
    }
}

class BFS {
    public static List<Integer> bfs(int V, List<Integer>[] adj) {
        List<Integer> ans = new ArrayList<>();
        boolean[] vis = new boolean[V];
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);

        while (!q.isEmpty()) {
            int curr = q.poll();
            vis[curr] = true;
            ans.add(curr);

            for (int i : adj[curr]) {
                if (!vis[i]) {
                    vis[i] = true;
                    q.offer(i);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // Create a graph given in the above diagram
        Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        List<Integer> v = bfs(4, g.adj);
        for (int i : v) {
            System.out.print(i + " "); // 0 1 2 3
        }
    }
}
