#include <bits/stdc++.h>
using namespace std;

int isNegativeWeight(int n, vector<vector<int>> edges)
{
    vector<int> dis(n, INT_MAX);
    dis[0] = 0;

    for (int i = 0; i < n - 1; i++)
    {
        for (auto edge : edges)
        {
            int src = edge[0];
            int dest = edge[1];
            int weight = edge[2];
            if (dis[src] != INT_MAX)
            {
                dis[dest] = min(dis[dest], dis[src] + weight);
            }
        }
    }

    for (auto edge : edges)
    {
        int src = edge[0];
        int dest = edge[1];
        int weight = edge[2];
        if (dis[src] != INT_MAX && dis[src] + weight < dis[dest])
            return 1;
    }
    return 0;
}