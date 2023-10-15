#include <bits/stdc++.h>
using namespace std;

void allPath(vector<vector<int>> &graph, int currnode, vector<bool> &visited, int n, vector<int> &currpath, vector<vector<int>> &res)
{
    if (currnode == n - 1)
    {
        res.push_back(currpath);
        return;
    }

    if (visited[currnode] == true)
        return;

    visited[currnode] = true;

    for (auto neighbour : graph[currnode])
    {
        currpath.push_back(neighbour);
        allPath(graph, neighbour, visited, n, currpath, res);
        currpath.pop_back();
    }
    visited[currnode] = false;
}

vector<vector<int>> PathSrcTarget(vector<vector<int>> &graph)
{
    vector<vector<int>> res;
    vector<int> currpath;
    int n = graph.size();
    vector<bool> visited(n);

    currpath.push_back(0);

    allPath(graph, 0, visited, n, currpath, res);
    return res;
}