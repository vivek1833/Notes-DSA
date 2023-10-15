#include <bits/stdc++.h>
using namespace std;

void findSubArray(vector<int> arr, int k)
{
    pair<int, int> ans;
    int sum = 0;

    for (int i = 0; i < k; i++)
        sum += arr[i];

    int found = 0;

    if (sum % 3 == 0)
    {
        ans = make_pair(0, k - 1);
        found = 1;
    }

    for (int j = k; j < arr.size(); j++)
    {

        if (found == 1)
            break;

        sum = sum + arr[j] - arr[j - k];

        if (sum % 3 == 0)
        {
            ans = make_pair(j - k + 1, j);
            found = 1;
        }
    }

    if (found == 0)
        ans = make_pair(-1, 0);

    if (ans.first == -1)
    {
        cout << -1;
    }
    else
    {
        for (int i = ans.first; i <= ans.second; i++)
        {
            cout << arr[i] << " ";
        }
    }
}

// Driver's code
int main()
{
    // Given array and K
    vector<int> arr = {84, 23, 45, 12, 56, 82};
    int K = 3;

    findSubArray(arr, K);

    return 0;
}
