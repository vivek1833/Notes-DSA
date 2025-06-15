#include <bits/stdc++.h>
using namespace std;

int minDifferenceAmongMaxMin(int arr[], int N, int K)
{
    sort(arr, arr + N);
    int res = INT_MAX;

    for (int i = 0; i <= (N - K); i++)
    {
        int curSeqDiff = arr[i + K - 1] - arr[i];
        res = min(res, curSeqDiff);
    }
    return res;
}

int main()
{
    int arr[] = {10, 20, 30, 100, 101, 102};
    int N = sizeof(arr) / sizeof(arr[0]);

    int K = 3;
    cout << minDifferenceAmongMaxMin(arr, N, K);
    return 0;
}
