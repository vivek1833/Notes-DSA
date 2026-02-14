/*
    Problem Link: https://www.geeksforgeeks.org/kruskals-minimum-spanning-tree-algorithm-greedy-algo-2/
    
    Kruskal's Algorithm - Minimum Spanning Tree using DSU
*/

import java.util.*;

class DSU {
    private int[] rank, parent;
    private int n;

    public DSU(int n) {
        rank = new int[n];
        parent = new int[n];
        this.n = n;

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void unionSet(int x, int y) {
        int xroot = find(x);
        int yroot = find(y);

        if (xroot == yroot) {
            return;
        }

        if (rank[xroot] < rank[yroot]) {
            parent[xroot] = yroot;
        } else if (rank[xroot] > rank[yroot]) {
            parent[yroot] = xroot;
        } else {
            parent[yroot] = xroot;
            rank[xroot]++;
        }
    }

    public boolean isSameSet(int x, int y) {
        return find(x) == find(y);
    }
}

class Graph {
    private List<int[]> edges;
    private int v;

    public Graph(int v) {
        this.v = v;
        edges = new ArrayList<>();
    }

    public void addEdge(int u, int v, int w) {
        edges.add(new int[]{w, u, v});
    }

    public void kruskal() {
        edges.sort((a, b) -> Integer.compare(a[0], b[0]));

        DSU s = new DSU(v); // Disjoint Programming Set
        int ans = 0;

        for (int[] e : edges) {
            int w = e[0];
            int x = e[1];
            int y = e[2];

            if (s.find(x) != s.find(y)) {
                s.unionSet(x, y);
                ans += w;
                System.out.println(x + "--" + y + "==" + w);
            }
        }
        System.out.println("Minimum Spanning Tree: " + ans);
    }
}
