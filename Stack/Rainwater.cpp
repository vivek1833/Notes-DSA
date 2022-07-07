#include <bits/stdc++.h>
using namespace std;

int Water(vector<int> A)
{
    stack<int> s;
    int n = A.size(), ans = 0;

    for (int i = 0; i < n; i++)
    {
        while (!s.empty() && A[s.top()] < A[i])
        {
            int curr = s.top();
            s.pop();
            if (s.empty())
            {
                break;
            }
            int diff = i - s.top() - 1;
            ans += (min(A[s.top()], A[i]) - A[curr]) * diff;
        }
        s.push(i);
    }
    return ans;
}

int main()
{
    vector<int> v = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
    cout << Water(v);       // 6
    return 0;
}