/*
    Problem Link: https://leetcode.com/problems/all-paths-from-source-to-target/
    
    All Paths From Source to Target
*/

import java.util.*;

class All_Paths_SrcToDes {
    private static void allPath(List<List<Integer>> graph, int currnode, boolean[] visited,
            int n, List<Integer> currpath, List<List<Integer>> res) {
        if (currnode == n - 1) {
            res.add(new ArrayList<>(currpath));
            return;
        }

        if (visited[currnode]) {
            return;
        }

        visited[currnode] = true;

        for (int neighbour : graph.get(currnode)) {
            currpath.add(neighbour);
            allPath(graph, neighbour, visited, n, currpath, res);
            currpath.remove(currpath.size() - 1);
        }
        visited[currnode] = false;
    }

    public static List<List<Integer>> PathSrcTarget(List<List<Integer>> graph) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> currpath = new ArrayList<>();
        int n = graph.size();
        boolean[] visited = new boolean[n];

        currpath.add(0);

        allPath(graph, 0, visited, n, currpath, res);
        return res;
    }
}
