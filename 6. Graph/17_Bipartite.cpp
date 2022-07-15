#include <bits/stdc++.h>
using namespace std;

bool isBipartite(vector<vector<int>> &graph)
{
    int n = graph.size();
    vector<int> color(n, -1);

    for (int i = 0; i < n; i++)
    {
        if (color[i] != -1)
            continue;

        if (hasEvenLength(graph, i, 0, color) == false)
            return false;
    }
}

bool hasEvenLength(vector<vector<int>> &graph, int node, int color, vector<int> &colorArr)
{
    if (colorArr[node] != -1)
        return colorArr[node] == color;

    colorArr[node] = color;

    for (int neighbour : graph[node])
    {
        if (hasEvenLength(graph, neighbour, 1 - color, colorArr) == false)
            return false;
    }

    return true;
}