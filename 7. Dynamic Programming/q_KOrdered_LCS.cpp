#include <bits/stdc++.h>
using namespace std;

#define MAX 10

// TC -> O(N*M*K)
int lcs(int dp[MAX][MAX][MAX], int arr1[], int n, int arr2[], int m, int k)
{
    if (k < 0)
        return -1e7;

    if (n < 0 || m < 0)
        return 0;

    int &ans = dp[n][m][k];

    if (ans != -1)
        return ans;

    ans = max(lcs(dp, arr1, n - 1, arr2, m, k), lcs(dp, arr1, n, arr2, m - 1, k));

    if (arr1[n - 1] == arr2[m - 1])
        ans = max(ans, 1 + lcs(dp, arr1, n - 1, arr2, m - 1, k));

    ans = max(ans, 1 + lcs(dp, arr1, n - 1, arr2, m - 1, k - 1));

    return ans;
}

int main()
{
    int k = 1;
    int arr1[] = {1, 2, 3, 4, 5};
    int arr2[] = {5, 3, 1, 4, 2};
    int n = sizeof(arr1) / sizeof(arr1[0]);
    int m = sizeof(arr2) / sizeof(arr2[0]);

    int dp[MAX][MAX][MAX];
    memset(dp, -1, sizeof(dp));

    cout << lcs(dp, arr1, n, arr2, m, k) << endl;

    return 0;
}
