/*
    Problem Link: https://www.geeksforgeeks.org/disjoint-set-data-structures/
    
    Disjoint Set Union (DSU) / Union-Find Data Structure
*/

import java.util.*;

class DSU {
    private int[] par, size, rank;

    public DSU(int v) {
        par = new int[v + 1];
        size = new int[v + 1];
        rank = new int[v + 1];
        Arrays.fill(size, 1);
        Arrays.fill(rank, 0);
        for (int i = 0; i < v; i++) {
            par[i] = i;
        }
    }

    public int findPar(int node) {
        if (par[node] == node) {
            return node;
        }
        return par[node] = findPar(par[node]);
    }

    public void unionBySize(int u, int v) {
        u = findPar(u);
        v = findPar(v);
        if (u == v) {
            return;
        }
        if (size[u] < size[v]) {
            par[u] = v;
            size[v] += size[u];
        } else {
            par[v] = u;
            size[u] += size[v];
        }
    }

    public void unionByRank(int u, int v) {
        u = findPar(u);
        v = findPar(v);
        if (u == v) {
            return;
        }
        if (rank[u] < rank[v]) {
            par[u] = v;
        } else if (rank[v] < rank[u]) {
            par[v] = u;
        } else {
            par[v] = u;
            rank[u]++;
        }
    }

    public int countComponents() {
        int cnt = 0;
        for (int i = 0; i < par.length; i++) {
            if (par[i] == i) {
                cnt++;
            }
        }
        return cnt;
    }
}
