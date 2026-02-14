/*
    Problem Link: https://www.geeksforgeeks.org/prims-minimum-spanning-tree-mst-greedy-algo-5/
    
    Prim's Algorithm - Minimum Spanning Tree
*/

import java.util.*;

class Prim_Algo {
    public static int spanningTree(int v, List<int[]>[] adj) {
        int mincost = 0;
        int[] costs = new int[v];
        Arrays.fill(costs, Integer.MAX_VALUE);

        costs[0] = 0;
        boolean[] visited = new boolean[v];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        pq.offer(new int[]{0, 0});

        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            int currnode = p[1];
            int currcost = p[0];

            if (visited[currnode]) {
                continue;
            }

            mincost += currcost;
            visited[currnode] = true;
            costs[currnode] = currcost;

            for (int i = 0; i < adj[currnode].size(); i++) {
                int nextnode = adj[currnode].get(i)[0];
                int nextcost = adj[currnode].get(i)[1];
                if (visited[nextnode]) {
                    continue;
                }

                pq.offer(new int[]{nextcost, nextnode});
            }
        }
        return mincost;
    }
}
