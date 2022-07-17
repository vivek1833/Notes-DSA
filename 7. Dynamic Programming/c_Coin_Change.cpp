#include <bits/stdc++.h>
using namespace std;

void solve()
{
    int m;
    cin >> m; 

    vector<int> s(m);

    for (int i = 0; i < m; i++)
        cin >> s[i];

    int x;
    cin >> x;

    vector<int> dp(x + 1, 0);
    dp[0] = 1;

    for (int i = 0; i < m; i++)
    {
        for (int j = 0; j < x + 1; j++)
        {
            if (j - s[i] >= 0)
                dp[j] += dp[j - s[i]];
        }
    }
    cout << dp[x] << endl;
}

int main()
{
    solve();
    return 0;
}