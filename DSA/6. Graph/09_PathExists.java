/*
    Problem Link: https://leetcode.com/problems/find-if-path-exists-in-graph/
    
    Find if Path Exists in Graph
*/

import java.util.*;

class PathExists {
    private static boolean pathexist(int src, int dest, List<List<Integer>> graph, boolean[] vis) {
        if (src == dest) {
            return true;
        }

        vis[src] = true;

        for (int i = 0; i < graph.get(src).size(); i++) {
            if (!vis[graph.get(src).get(i)]) {
                if (pathexist(graph.get(src).get(i), dest, graph, vis)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean validpath(int n, List<List<Integer>> edges, int src, int dest) {
        List<List<Integer>> g = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<>());
        }
        
        for (int i = 0; i < edges.size(); i++) {
            int v1 = edges.get(i).get(0);
            int v2 = edges.get(i).get(1);
            g.get(v1).add(v2);
            g.get(v2).add(v1);
        }

        boolean[] vis = new boolean[n];
        return pathexist(src, dest, g, vis);
    }
}
