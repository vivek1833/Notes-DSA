#include <bits/stdc++.h>
using namespace std;

class DisSet
{
    int *rank, *parent, n;

public:
    DisSet(int n)
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