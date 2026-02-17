/*
    Problem Link: https://www.geeksforgeeks.org/kruskals-minimum-spanning-tree-algorithm-greedy-algo-2/
    
    Kruskal's Algorithm - Minimum Spanning Tree using DSU
*/

import java.util.Arrays;

class DSU {
    int[] par;
    int[] size;
    
    public DSU(int v) {
        par = new int[v + 1];
        size = new int[v + 1];
        for(int i=0; i<=v; i++) {
            par[i] = i;
            size[i] = 1;
        }
    }
    
    public int findPar(int node) {
        if(par[node] == node) return node;
        return par[node] = findPar(par[node]);
    }
    
    public void union(int u, int v) {
        u = findPar(u);
        v = findPar(v);
        if(u == v) return;
        
        if(size[u] < size[v]) {
            par[u] = v;
            size[v] += size[u];
        } else {
            par[v] = u;
            size[u] += size[v];
        }
    }
}

class Solution {
    static int kruskalsMST(int V, int[][] edges) {
        DSU ds = new DSU(V);
        Arrays.sort(edges, (a,b) -> a[2] - b[2]);
        int cnt = 0;
        
        for(int[] edge: edges) {
            int u=edge[0], v=edge[1], w=edge[2];
            
            if(ds.findPar(u) != ds.findPar(v)) {
                cnt += w;
                ds.union(u,v);
            }
        }
        
        return cnt;
    }
}
