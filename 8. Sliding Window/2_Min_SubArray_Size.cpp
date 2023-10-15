#include <bits/stdc++.h>
using namespace std;

int smallestSubarraySum(int arr[], int n, int x)
{
    int sum = 0, min_len = n + 1, start = 0, end = 0;
    while (end < n)
    {
        while (sum < x && end < n)
        {
            sum += arr[end++];
        }
        while (sum >= x && start < n)
        {
            min_len = min(min_len, end - start);
            sum -= arr[start++];
        }
    }
    return min_len;
}

int main()
{
    int arr[] = {1, 4, 43, 6, 10, 19};
    int n = 6;
    int x = 51;
    cout << smallestSubarraySum(arr, n, x);
    return 0;
}