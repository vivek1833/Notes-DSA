#include <bits/stdc++.h>
using namespace std;

bool iscyclic(int v, vector<int> adj[])
{
    vector<int> indeg(v, 0);
    for (int i = 0; i < v; i++)
    {
        for (int j : adj[i])
            indeg[j]++;
    }

    queue<int> q;
    int ans = 0;
    unordered_set<int> vis;

    for (int i = 0; i < v; i++)
    {
        if (indeg[i] == 0)
        {
            q.push(i);
            ans += 1;
        }
    }

    while (!q.empty())
    {
        int currvertex = q.front();
        q.pop();
        if (vis.find(currvertex) != vis.end())
            continue;

        vis.insert(currvertex);
        for (int neighbour : adj[currvertex])
        {
            indeg[neighbour] -= 1;
            if (indeg[neighbour] == 0)
            {
                q.push(neighbour);
                ans += 1;
            }
        }
    }
    if (ans == v)
        return false;
    return true;
}