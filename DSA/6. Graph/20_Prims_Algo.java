import java.util.*;

/*
    Problem Link: https://www.geeksforgeeks.org/problems/minimum-spanning-tree/1
    
    The graph is provided as a list of edges, where each edge is represented as [u, v, w], indicating an edge between vertex u and vertex v with edge weight w.

    Input: V = 3, E = 3, Edges = [[0, 1, 5], [1, 2, 3], [0, 2, 1]]
    Output: 4 (The Spanning Tree resulting in a weight of 4)
*/

// TC: O(E logE)
// SC: O(E)
class Pair {
    int node, dist;

    Pair(int node, int dist) {
        this.node = node;
        this.dist = dist;
    }
}

class Prim_Algo {
    public static int spanningTree(int v, List<int[]>[] adj) {
        boolean[] visited = new boolean[v];
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist);
        pq.offer(new Pair(0, 0));
        int ans = 0;

        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            int node = curr.node, dist = curr.dist;
            
            if(visited[node])    continue;
            visited[node] = true;
            ans += dist;
            
            for (int[] neigh: adj[node]) {
                if(!visited[neigh[0]]) {
                    pq.offer(new Pair(neigh[0], neigh[1]));
                }
            }
        }   

        return ans;
    }
}
