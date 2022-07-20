#include <bits/stdc++.h>
using namespace std;

bool isPalindrome(int n)
{
    int temp = n;
    int rev = 0;

    while (temp)
    {
        rev = rev * 10 + temp % 10;
        temp /= 10;
    }
    return rev == n;
}

int PalindromeSubarray(vector<int> arr, int k)
{
    int num = 0;
    for (int i = 0; i < k; i++)
        num = num * 10 + arr[i];

    if (isPalindrome(num))
        return 0;

    for (int j = k; j < arr.size(); j++)
    {
        num = (num % (int)pow(10, k - 1)) * 10 + arr[j];

        if (isPalindrome(num))
            return j - k + 1;
    }
    return -1;
}

int main()
{
    vector<int> arr = {2, 3, 5, 1, 1, 5};
    int k = 4;
    int ans = PalindromeSubarray(arr, k); // 5 1 1 5

    if (ans == -1)
        cout << "No Palindrome Subarray";

    else
    {
        for (int i = ans; i < ans + k; i++)
            cout << arr[i] << " ";
    }

    return 0;
}