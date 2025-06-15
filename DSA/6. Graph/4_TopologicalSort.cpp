#include <bits/stdc++.h>
using namespace std;

int main()
{
    int n, m, cnt = 0;
    cin >> n >> m;

    vector<vector<int>> adj(n);
    vector<int> indeg(n, 0);

    for (int i = 0; i < m; i++)
    {
        int u, v;
        cin >> u >> v;
        adj[u].push_back(v);
        indeg[v]++;
    }

    queue<int> q;
    for (int i = 0; i < n; i++)
        if (indeg[i] == 0)
            q.push(i);

    while (!q.empty())
    {
        int u = q.front();
        q.pop();
        cout << u << " ";
        cnt++;
        for (int v : adj[u])
        {
            indeg[v]--;
            if (indeg[v] == 0)
                q.push(v);
        }
    }
}