import java.util.*;

/*
    Problem Link: https://leetcode.com/problems/making-a-large-island/description/

    You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.
    Return the size of the largest island in grid after applying this operation.
    An island is a 4-directionally connected group of 1s.

    ----------------------------------
    
    Example 1:
        Input: grid = [[1,0],[0,1]]
        Output: 3
        Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
    
    Example 2:
        Input: grid = [[1,1],[1,0]]
        Output: 4
        Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
    
    Example 3:
        Input: grid = [[1,1],[1,1]]
        Output: 4
        Explanation: Can't change any 0 to 1, only one island with area = 4.
    
    ----------------------------------
    
    Constraints:
        - n == grid.length
        - n == grid[i].length
        - 1 <= n <= 500
        - grid[i][j] is either 0 or 1.
*/

class DSU {
    int[] par;
    int[] size;
    
    public DSU(int v) {
        par = new int[v + 1];
        size = new int[v + 1];
        for (int i = 0; i <= v; i++) {
            par[i] = i;
            size[i] = 1;
        }
    }
    
    public int findPar(int node) {
        if (par[node] == node) return node;
        return par[node] = findPar(par[node]);
    }
    
    public void union(int u, int v) {
        u = findPar(u);
        v = findPar(v);
        if (u == v) return;
        
        if (size[u] < size[v]) {
            par[u] = v;
            size[v] += size[u];
        } else {
            par[v] = u;
            size[u] += size[v];
        }
    }
}

class Solution {
    public int largestIsland(int[][] grid) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int n = grid.length;
        DSU ds = new DSU(n * n);
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) continue;
                int curr = i * n + j;

                for (int it = 0; it < 4; it++) {
                    int ni = i + dx[it];
                    int nj = j + dy[it];

                    if (ni >= 0 && nj >= 0 && ni < n && nj < n && grid[ni][nj] == 1) {
                        int nc = ni * n + nj;
                        ds.union(curr, nc);
                    }
                }
            }
        }
        
        int mx = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) continue;
                
                Set<Integer> set = new HashSet<>(); 
                
                for (int it = 0; it < 4; it++) {
                    int ni = i + dx[it];
                    int nj = j + dy[it];
                    if (ni >= 0 && nj >= 0 && ni < n && nj < n && grid[ni][nj] == 1) {
                        int nc = ni * n + nj;
                        set.add(ds.findPar(nc));
                    }
                }
                
                int curr_mx = 0;
                for (int parent : set) {
                    curr_mx += ds.size[parent];
                }
                
                mx = Math.max(mx, curr_mx + 1);
            }
        }
        
        return (mx == Integer.MIN_VALUE ? n * n : mx);
    }
}