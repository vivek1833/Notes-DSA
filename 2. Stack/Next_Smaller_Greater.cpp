#include <bits/stdc++.h>
using namespace std;

vector<int> Smaller(vector<int> &arr, int n)
{
    stack<int> st;
    vector<int> ans(n);
    st.push(-1);

    for (int i = n - 1; i >= 0; i--)
    {
        while (st.top() >= arr[i])
        {
            st.pop();
        }
        ans[i] = st.top();
        st.push(arr[i]);
    }
    return ans;
}

vector<int> Greater(vector<int> arr, int n)
{
    stack<int> s;
    vector<int> res(n);

    for (int i = n - 1; i >= 0; i--)
    {

        if (!s.empty())
        {
            while (!s.empty() && s.top() <= arr[i])
            {
                s.pop();
            }
        }
        res[i] = (s.empty() ? -1 : s.top());
        s.push(arr[i]);
    }
    return res;
}

int main()
{
    vector<int> arr = {6, 8, 0, 5, 3};

    vector<int> ans = Smaller(arr, arr.size());
    // 0 0 -1 3 -1

    vector<int> ans = Greater(arr, arr.size());
    // 8 -1 5 -1 -1

    for (int i = 0; i < ans.size(); i++)
    {
        cout << ans[i] << " ";
    }
    return 0;
}