#include <bits/stdc++.h>
using namespace std;

vector<int> Span(int price[], int n)
{
    vector<int> span(n);
    stack<int> st;

    st.push(0);
    span[0] = 1;

    for (int i = 1; i < n; i++)
    {
        int currPrice = price[i];
        while (!st.empty() && currPrice >= price[st.top()])
        {
            st.pop();
        }
        if (st.empty())
            span[i] = i + 1;

        else
            span[i] = i - st.top();

        st.push(i);
    }
    return span;
}