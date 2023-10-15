#include <bits/stdc++.h>
using namespace std;

vector<int> MaxWindow(vector<int> &nums, int k)
{
    deque<int> dq;
    vector<int> res;
    for (int i = 0; i < nums.size(); i++)
    {
        while (!dq.empty() && dq.front() == i - k)
            dq.pop_front();

        while (!dq.empty() && nums[dq.back()] < nums[i])
            dq.pop_back();

        dq.push_back(i);
        if (i >= k - 1)
            res.push_back(nums[dq.front()]);
    }
    return res;
}

int main()
{
    vector<int> v = {1, 3, -1, -3, 5, 3, 6, 7};
    vector<int> res = MaxWindow(v, 3);
    for (auto i : res)
        cout << i << " ";
    // 3 3 5 5 6 7

    return 0;
}