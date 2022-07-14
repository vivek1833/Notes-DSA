#include <bits/stdc++.h>
using namespace std;

bool dfs(int node, vector<int> &vis, vector<int> &res, vector<int> adj[])
{
    vis[node] = 1;    // TC => O(V+E)
    res[node] = 1;
    for (auto i : adj[node])
    {
        if (vis[i] == 0)
        {
            if (dfs(i, vis, res, adj))
                return true;
        }
        else if (res[i] == 1)
            return true;
    }
    res[node] = 0;
    return false;
}

bool iscyclic(int v, vector<int> adj[])
{
    vector<int> vis(v, 0);
    vector<int> res(v, 0);

    for (int i = 0; i < v; i++)
    {
        if (vis[i] == 0)
        {
            if (vis[i] == 0)
            {
                if (dfs(i, vis, res, adj))
                {
                    return true;
                }
            }
        }
    }
    return false;
}
