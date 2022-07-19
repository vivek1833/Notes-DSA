#include <bits/stdc++.h>
using namespace std;

const int MAX_CHAR = 256;

int countSub(string str)
{
    vector<int> last(MAX_CHAR, -1);
    int n = str.length();
    int dp[n + 1];
    dp[0] = 1;

    for (int i = 1; i <= n; i++)
    {
        dp[i] = 2 * dp[i - 1];

        if (last[str[i - 1]] != -1)
            dp[i] = dp[i] - dp[last[str[i - 1]]];

        last[str[i - 1]] = (i - 1);
    }
    return dp[n];
}

int main()
{
    cout << countSub("gfg");
    return 0;
}
