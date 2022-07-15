#include <bits/stdc++.h>
using namespace std;

int spanningTree(int v, vector<vector<int>> adj[])
{
    int mincost = 0;
    vector<int> costs(v, INT_MAX);

    costs[0] = 0;
    vector<bool> visited(v, false);
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;

    pq.push({0, 0});

    while (!pq.empty())
    {
        pair<int, int> p = pq.top();
        int currnode = p.second;
        int currcost = p.first;
        pq.pop();

        if (visited[currnode])
            continue;

        mincost += currcost;
        visited[currnode] = true;
        costs[currnode] = currcost;

        for (int i = 0; i < adj[currnode].size(); i++)
        {
            int nextnode = adj[currnode][i][0];
            int nextcost = adj[currnode][i][1];
            if (visited[nextnode])
                continue;

            pq.push({nextcost, nextnode});
        }
    }
    return mincost;
}