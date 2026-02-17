/*
    Problem Link: https://www.geeksforgeeks.org/disjoint-set-data-structures/
    
    Disjoint Set Union (DSU) / Union-Find Data Structure
*/

// TC: O(1)
// SC: O(n)
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
