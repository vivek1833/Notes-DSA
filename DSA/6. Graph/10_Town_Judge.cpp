#include <bits/stdc++.h>
using namespace std;

int judge(int n, vector<vector<int>> &trust)
{
    vector<int> indeg(n + 1, 0);
    vector<int> outdeg(n + 1, 0);

    for (int i = 0; i < trust.size(); i++)
    {
        int v1 = trust[i][0];
        int v2 = trust[i][1];
        outdeg[v1] += 1;
        indeg[v2] += 1;
    }

    for (int i = 1; i <= n; i++)
    {
        if (outdeg[i] == 0 && indeg[i] == n - 1)
            return i;
    }
    return -1;
}