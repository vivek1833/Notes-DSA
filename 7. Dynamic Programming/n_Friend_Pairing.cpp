#include <bits/stdc++.h>
using namespace std;

// USING DYNAMIC PROGRAMMING   TC-> O(n) SC->O(n)
int FriendsPair_DP(int n)
{
    int dp[n + 1];

    for (int i = 0; i <= n; i++)
    {
        if (i <= 2)
            dp[i] = i;
        else
            dp[i] = dp[i - 1] + (i - 1) * dp[i - 2];
    }

    return dp[n];
}

// ANOTHER WITHOUT DP TC->O(n) SC->O(1)
int FriendsPair(int n)
{
    int a = 1, b = 2, c = 0;

    if (n <= 2)
        return n;

    for (int i = 3; i <= n; i++)
    {
        c = b + (i - 1) * a;
        a = b;
        b = c;
    }

    return c;
}

int main()
{
    int n = 4;
    cout << FriendsPair_DP(n) << endl;
    cout << FriendsPair(n);
    return 0;
}
