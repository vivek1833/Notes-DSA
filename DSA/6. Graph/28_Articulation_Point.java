import java.util.*;

/*
    Problem Link: https://www.geeksforgeeks.org/problems/articulation-point2616/1

    You are given an undirected graph with V vertices and E edges. The graph is represented as a 2D array edges[][], where each element edges[i] = [u, v] indicates an undirected edge between vertices u and v.
    Your task is to return all the articulation points (or cut vertices) in the graph.
    An articulation point is a vertex whose removal, along with all its connected edges, increases the number of connected components in the graph.

    -----------------------

    Example 1:
        Input: V = 5, edges[][] = [[0, 1], [1, 4], [4, 3], [4, 2], [2, 3]]
        Output: [1, 4]
        Explanation: Removing the vertex 1 or 4 will disconnects the graph as-
    
    Example 2:
        Input: V = 4, edges[][] = [[0, 1], [0, 2]]
        Output: [0]
        Explanation: Removing the vertex 0 will increase the number of disconnected components to 3.  
*/

// TC: O(V + E)
// SC: O(V + E)
class Solution {
    private int timer = 1;

    private void dfs(int node, int parent, int[] vis,
                    int[] tin, int[] low, int[] mark,
                    ArrayList<ArrayList<Integer>> adj) {

        vis[node] = 1;
        tin[node] = low[node] = timer;
        timer++;
        int child = 0;

        for (Integer it : adj.get(node)) {

            if (it == parent) continue;

            if (vis[it] == 0) {

                dfs(it, node, vis, tin, low, mark, adj);
                low[node] = Math.min(low[node], low[it]);

                // node --- it
                if (low[it] >= tin[node] && parent != -1) {
                    mark[node] = 1;
                }

                child++;
            } else {
                low[node] = Math.min(low[node], tin[it]);
            }
        }

        if (child > 1 && parent == -1) {
            mark[node] = 1;
        }
    }

    // Function to return articulation points of given graph.
    public ArrayList<Integer> articulationPoints(int n,
                                                ArrayList<ArrayList<Integer>> adj) {

        int[] vis = new int[n];
        int[] tin = new int[n];
        int[] low = new int[n];
        int[] mark = new int[n];

        for (int i = 0; i < n; i++) {
            if (vis[i] == 0) {
                dfs(i, -1, vis, tin, low, mark, adj);
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (mark[i] == 1) {
                ans.add(i);
            }
        }

        if (ans.size() == 0) {
            ans.add(-1);
        }

        return ans;
    }
}