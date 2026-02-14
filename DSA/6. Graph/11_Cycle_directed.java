/*
    Problem Link: https://leetcode.com/problems/course-schedule/
    
    Detect Cycle in Directed Graph
*/

// Cycle detection in directed graph
// 0 -> not vis
// 1 -> vis in current cycle
// 2 -> vis and safe

import java.util.*;

class Cycle_directed {
    private boolean dfs(int curr, int n, List<Integer> vis, List<List<Integer>> adj)
    {
        vis.set(curr, 1);

        for(int neigh : adj.get(curr)) {
            if(vis.get(neigh)==1)   return true;
            if(vis.get(neigh)==0 && dfs(neigh,n,vis,adj))    return true;
        }

        vis.set(curr, 2);
        return false;
    }
    
    public boolean canFinish(int t, int[][] nums) 
    {
        List<List<Integer>> adj = new ArrayList<>(t);  
        List<Integer> vis = new ArrayList<>();
        int n=nums.length;

        for(int i=0; i<t; i++) {
            adj.add(new ArrayList<>());
            vis.add(0);
        }

        for(int i=0; i<n; i++) {
            int u=nums[i][0];
            int v=nums[i][1];
            adj.get(u).add(v);
        }

        for(int i=0; i<t; i++) {
            if(vis.get(i)==0) {
                if(dfs(i,n,vis,adj)) {
                    return false;
                }
            }
        }

        return true;
    }
}