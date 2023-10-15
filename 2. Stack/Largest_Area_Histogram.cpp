#include <bits/stdc++.h>
using namespace std;

int LargestRectangleArea(vector<int> &height)
{
    int max_area = 0;
    stack<int> st;

    for (int i = 0; i < height.size(); i++)
    {
        while (!st.empty() && height[st.top()] >= height[i])
        {
            int h = height[st.top()];
            st.pop();
            int w = (st.empty() ? i : i - st.top() - 1);
            max_area = max(max_area, h * w);
        }
        st.push(i);
    }
    return max_area;
}

int main()
{
    vector<int> height = {2, 1, 5, 6, 2, 3};    // 10
    cout << LargestRectangleArea(height) << endl;

    return 0;
}