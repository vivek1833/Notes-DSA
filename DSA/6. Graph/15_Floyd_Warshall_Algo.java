/*
    Problem Link: https://www.geeksforgeeks.org/floyd-warshall-algorithm-dp-16/
    
    Floyd-Warshall Algorithm - All Pairs Shortest Path
*/

import java.util.*;

class Floyd_Warshall_Algo {
    public static void short_dis(List<List<Integer>> matrix) {
        int v = matrix.size();
        List<List<Integer>> costs = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            costs.add(new ArrayList<>());
            for (int j = 0; j < v; j++) {
                costs.get(i).add(matrix.get(i).get(j));
            }
        }

        for (int k = 0; k < v; k++) {
            for (int i = 0; i < v; i++) {
                for (int j = 0; j < v; j++) {
                    if (costs.get(i).get(k) != -1 && costs.get(k).get(j) != -1) {
                        if (costs.get(i).get(j) == -1) {
                            costs.get(i).set(j, costs.get(i).get(k) + costs.get(k).get(j));
                        } else {
                            costs.get(i).set(j, Math.min(costs.get(i).get(j), 
                                                          costs.get(i).get(k) + costs.get(k).get(j)));
                        }
                    }
                }
            }
        }
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                matrix.get(i).set(j, costs.get(i).get(j));
            }
        }
    }
}
