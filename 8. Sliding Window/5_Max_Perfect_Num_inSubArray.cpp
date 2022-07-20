#include <bits/stdc++.h>
using namespace std;

int isPerfect(int N)
{
    int sum = 1;

    for (int i = 2; i < sqrt(N); i++)
    {
        if (N % i == 0)
        {
            if (i == N / i)
                sum += i;

            else
                sum += i + N / i;
        }
    }

    if (sum == N && N != 1)
        return 1;

    return 0;
}

int maxSum(int arr[], int N, int K)
{
    if (N < K)
    {
        cout << "Invalid";
        return -1;
    }

    int res = 0;
    for (int i = 0; i < K; i++)
        res += arr[i];

    int curr_sum = res;

    for (int i = K; i < N; i++)
    {
        curr_sum += arr[i] - arr[i - K];
        res = max(res, curr_sum);
    }
    return res;
}

int max_PerfectNumbers(int arr[], int N, int K)
{
    for (int i = 0; i < N; i++)
    {
        arr[i] = isPerfect(arr[i]) ? 1 : 0;
    }
    return maxSum(arr, N, K);
}

int main()
{
    int arr[] = {28, 2, 3, 6, 496, 99, 8128, 24};
    int K = 4;
    int N = sizeof(arr) / sizeof(arr[0]);

    cout << max_PerfectNumbers(arr, N, K);

    return 0;
}