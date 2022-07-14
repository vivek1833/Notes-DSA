#include <bits/stdc++.h>
using namespace std;

class graph
{
    int node;
    int ver;

public:
    graph(int n, int v)
    {
        this->node = n;
        this->ver = v;
    }

    vector<int> AdjacencyList()
    {
        vector<int> g[ver + 1];
        for (int i = 0; i < ver; i++)
        {
            int x, y;
            cin >> x >> y;
            g[x].push_back(y);
            g[y].push_back(x);
        }
        return g[ver + 1];
    }

    vector<vector<int>> AdjacencyMatrix()
    {
        vector<vector<int>> g;
        for (int i = 0; i < ver; i++)
        {
            int x, y;
            cin >> x >> y;
            g[x][y] = 1;
            g[y][x] = 1;
        }
        return g;
    }
};

void PrintGraph(int node, vector<vector<int>> g)
{
    for (int i = 1; i < node + 1; i++)
    {
        for (int j = 1; j < node + 1; j++)
        {
            cout << g[i][j] << " ";
        }
        cout << endl;
    }
}

void Print(int node, vector<int> g[])
{
    for (int i = 1; i < node + 1; i++)
    {
        cout << i << "->";
        for (int x : g[i])
        {
            cout << x << " ";
        }
        cout << endl;
    }
}

int main()
{
    // int n, m; // n: nodes, m: vertices
    // cin >> n >> m;

    // vector<vector<int>> adjm(n + 1, vector<int>(n + 1, 0)); // Adjacency Matrix

    // AdjacencyMatrix(m, adjm);
    // PrintGraph(n, adjm);

    // vector<int> adjL[n + 1];
    // AdjacencyList(m, adjL);

    // Print(n, adjL);

    graph g(7, 7);

    Print(7, &g.AdjacencyList());
    PrintGraph(7, g.AdjacencyMatrix());
}
