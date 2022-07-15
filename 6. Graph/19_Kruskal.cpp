#include <bits/stdc++.h>
using namespace std;

class DSU    // Disjoint Set Program from Previous
{
    int *rank, *parent, n;

public:
    DSU(int n)
    {
        rank = new int[n];
        parent = new int[n];
        this->n = n;

        for (int i = 0; i < n; i++)
            parent[i] = i;
    }

    int find(int x)
    {
        if (parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    void unionSet(int x, int y)
    {
        int xroot = find(x);
        int yroot = find(y);

        if (xroot == yroot)
            return;

        if (rank[xroot] < rank[yroot])
        {
            parent[xroot] = yroot;
        }

        else if (rank[xroot] > rank[yroot])
        {
            parent[yroot] = xroot;
        }

        else
        {
            parent[yroot] = xroot;
            rank[xroot]++;
        }
    }

    bool isSameSet(int x, int y)
    {
        return find(x) == find(y);
    }
};

class graph
{
    vector<vector<int>> edges;
    int v;

public:
    graph(int v)
    {
        this->v = v;
    }

    void addEdge(int u, int v, int w)
    {
        edges.push_back({w, u, v});
    }

    void kruskal()
    {
        sort(edges.begin(), edges.end());

        DSU s(v); // Disjoint Programming Set
        int ans = 0;

        for (auto e : edges)
        {
            int w = e[0];
            int x = e[1];
            int y = e[2];

            if (s.find(x) != s.find(y))
            {
                s.unionSet(x, y);
                ans += w;
                cout << x << "--" << y << "==" << w << endl;
            }
        }
        cout << "Minimum Spanning Tree: " << ans << endl;
    }
};