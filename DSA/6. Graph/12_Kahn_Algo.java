/*
    Problem Link: https://www.geeksforgeeks.org/detect-cycle-in-a-directed-graph-using-bfs/
    
    Kahn's Algorithm - Detect Cycle in Directed Graph using BFS
*/

import java.util.*;

class Kahn_Algo {
    public static boolean iscyclic(int v, List<Integer>[] adj) {
        int[] indeg = new int[v];
        for (int i = 0; i < v; i++) {
            for (int j : adj[i]) {
                indeg[j]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        int ans = 0;
        Set<Integer> vis = new HashSet<>();

        for (int i = 0; i < v; i++) {
            if (indeg[i] == 0) {
                q.offer(i);
                ans += 1;
            }
        }

        while (!q.isEmpty()) {
            int currvertex = q.poll();
            if (vis.contains(currvertex)) {
                continue;
            }

            vis.add(currvertex);
            for (int neighbour : adj[currvertex]) {
                indeg[neighbour] -= 1;
                if (indeg[neighbour] == 0) {
                    q.offer(neighbour);
                    ans += 1;
                }
            }
        }
        if (ans == v) {
            return false;
        }
        return true;
    }
}
