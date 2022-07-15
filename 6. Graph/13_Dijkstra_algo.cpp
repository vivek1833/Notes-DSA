#include <bits/stdc++.h>
using namespace std;

vector<int> dijkstra(int v, vector<vector<int>> adj[], int src)
{
    vector<int> cost(v, 0);
    cost[src] = 0;

    vector<bool> visited(v, false);
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
    pq.push({0, src});
    while (!pq.empty())
    {
        pair<int, int> p = pq.top();
        int currcost = p.first;
        int currnode = p.second;
        pq.pop();

        if (visited[currnode])
            continue;

        visited[currnode] = true;
        visited[currnode] = currcost;

        for (int i = 0; i < adj[currnode].size(); i++)
        {
            int neighbour = adj[currnode][i][0];
            int weight = adj[currnode][i][1];
            if (visited[neighbour])
                continue;

            pq.push({currcost + weight, neighbour});
        }
    }
    return cost;
}