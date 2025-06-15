#include <bits/stdc++.h>
using namespace std;

void short_dis(vector<vector<int>> &matrix)
{
    int v = matrix.size();
    vector<vector<int>> costs(matrix.size(), vector<int>(matrix.size()));

    for (int i = 0; i < v; i++)
    {
        for (int j = 0; j < v; j++)
            costs[i][j] = matrix[i][j];
    }

    for (int k = 0; k < v; k++)
    {
        for (int i = 0; i < v; i++)
        {
            for (int j = 0; j < v; j++)
            {
                if (costs[i][k] != -1 && costs[k][j] != -1)
                {
                    if (costs[i][j] == -1)
                        costs[i][j] = costs[i][k] + costs[k][j];

                    else
                        costs[i][j] = min(costs[i][j], costs[i][k] + costs[k][j]);
                }
            }
        }
    }
    for (int i = 0; i < v; i++)
    {
        for (int j = 0; j < v; j++)
            matrix[i][j] = costs[i][j];
    }
}