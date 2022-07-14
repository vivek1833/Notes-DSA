#include <bits/stdc++.h>
using namespace std;

class Graph
{
    int node;
    int vertices;
    vector<vector<int>> adj; // Matrix representation of graph

public:
    Graph(int n, int v)
    {
        node = n;
        vertices = v;
        adj.resize(node);
    }

    void edge(int u, int v)
    {
        adj[u][v] = 1;
        adj[v][u] = 1;
    }

    void Print()
    {
        for (int i = 0; i < node; i++)
        {
            cout << i << " -> ";
            for (auto j : adj[i])
                cout << j << " ";
            cout << endl;
        }
    }
};

int main()
{
    Graph g(3, 3);
    g.edge(1, 2);
    g.edge(2, 3);
    g.edge(3, 1);

    g.Print();

    return 0;
}