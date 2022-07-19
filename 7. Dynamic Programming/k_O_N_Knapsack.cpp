#include <bits/stdc++.h>
using namespace std;

int unboundedKnapsack(int W, int wt[], int val[], int idx, vector<vector<int>> &dp)
{
    if (idx == 0)
        return (W / wt[0]) * val[0];

    if (dp[idx][W] != -1)
        return dp[idx][W];

    int notTake = 0 + unboundedKnapsack(W, wt, val, idx - 1, dp);

    int take = INT_MIN;
    if (wt[idx] <= W)
        take = val[idx] + unboundedKnapsack(W - wt[idx], wt, val, idx, dp);

    return dp[idx][W] = max(take, notTake);
}

int main()
{
    int W = 100;
    int val[] = {10, 30, 20};
    int wt[] = {5, 10, 15};
    int n = sizeof(val) / sizeof(val[0]);
    vector<vector<int>> dp(n, vector<int>(W + 1, -1));
    cout << unboundedKnapsack(W, wt, val, n - 1, dp);
    return 0;
}