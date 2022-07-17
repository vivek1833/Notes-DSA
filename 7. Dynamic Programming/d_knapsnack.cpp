#include <bits/stdc++.h>
using namespace std;

int knapsack()
{
    int n;
    cin >> n;

    vector<int> w(n);
    vector<int> v(n);

    for (int i = 0; i < n; i++)
        cin >> w[i];

    for (int i = 0; i < n; i++)
        cin >> v[i];

    int x;
    cin >> x;

    vector<vector<int>> dp(n + 1, vector<int>(x + 1, 0));

    for (int i = 1; i < n + 1; i++)
    {
        for (int j = 0; j < x + 1; j++)
        {
            dp[i][j] = dp[i - 1][j];
            if (j - w[i - 1] >= 0)
                dp[i][j] = max(dp[i][j], dp[i - 1][j - w[i - 1]] + v[i - 1]);
        }
    }
    cout << dp[n][x] << endl;
}

int main()
{
    knapsack();
    return 0;
}