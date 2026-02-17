/*
    Problem Link: https://www.geeksforgeeks.org/problems/distance-from-the-source-bellman-ford-algorithm/1
    
    Bellman-Ford Algorithm - Detect Negative Weight Cycle
    - Do n-1 relaxations
    - nth relaxation check for negative weight cycle
*/

import java.util.*;

class Bellmann_Ford_Algo {
    public static int isNegativeWeight(int n, List<List<Integer>> edges) {
        int[] dis = new int[n];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[0] = 0;

        for (int i = 0; i < n - 1; i++) {
            for (List<Integer> edge : edges) {
                int src = edge.get(0);
                int dest = edge.get(1);
                int weight = edge.get(2);
                if (dis[src] != Integer.MAX_VALUE) {
                    dis[dest] = Math.min(dis[dest], dis[src] + weight);
                }
            }
        }

        for (List<Integer> edge : edges) {
            int src = edge.get(0);
            int dest = edge.get(1);
            int weight = edge.get(2);
            if (dis[src] != Integer.MAX_VALUE && dis[src] + weight < dis[dest]) {
                return 1;   // Negative Weight Cycle
            }
        }

        return 0;
    }
}
