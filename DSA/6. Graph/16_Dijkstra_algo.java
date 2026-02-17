/*
    Problem Link: https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/
    
    Dijkstra's Shortest Path Algorithm
*/

import java.util.*;

class Dijkstra_algo {
    public static int[] dijkstra(int v, List<int[]>[] adj, int src) {
        int[] cost = new int[v];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[src] = 0;

        boolean[] visited = new boolean[v];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{0, src});
        
        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            int currcost = p[0];
            int currnode = p[1];

            if (visited[currnode]) {
                continue;
            }

            visited[currnode] = true;
            cost[currnode] = currcost; // Fixed: was setting visited[currnode] = currcost

            for (int i = 0; i < adj[currnode].size(); i++) {
                int neighbour = adj[currnode].get(i)[0];
                int weight = adj[currnode].get(i)[1];
                if (visited[neighbour]) {
                    continue;
                }

                pq.offer(new int[]{currcost + weight, neighbour});
            }
        }
        return cost;
    }
}
