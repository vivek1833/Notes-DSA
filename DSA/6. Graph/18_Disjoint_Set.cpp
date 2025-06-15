#include <bits/stdc++.h>
using namespace std;

class DSU
{
    vector<int> par, size, rank;

    DSU(int v)
    {
        par.resize(v + 1);
        size.resize(v + 1, 1);
        rank.resize(v + 1, 0);
        for (int i = 0; i < v; i++)
            par[i] = i;
    }

    int findPar(int node)
    {
        if (par[node] == node)
            return node;
        return par[node] = findPar(par[node]);
    }

    void unionBySize(int u, int v)
    {
        u = findPar(u), v = findPar(v);
        if (u == v)
            return;
        if (size[u] < size[v])
        {
            par[u] = v;
            size[v] += size[u];
        }
        else
        {
            par[v] = u;
            size[u] += size[v];
        }
    }

    void unionByRank(int u, int v)
    {
        u = findPar(u), v = findPar(v);
        if (u == v)
            return;
        if (rank[u] < rank[v])
        {
            par[u] = v;
        }
        else if (rank[v] < rank[u])
        {
            par[v] = u;
        }
        else
        {
            par[v] = u;
            rank[u]++;
        }
    }

    int countComponents()
    {
        int cnt = 0;
        for (int i = 0; i < par.size(); i++)
        {
            if (par[i] == i)
                cnt++;
        }
        return cnt;
    }
};