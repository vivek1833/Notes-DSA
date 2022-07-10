#include <bits/stdc++.h>
using namespace std;

void SortedInsert(stack<int> &st, int x)
{
    if (st.empty() || st.top() >= x) // 1 2 3 4 5
        st.push(x);
    // if (st.empty() || (!st.empty() && st.top() < x))
    // 5 4 3 2 1
    else
    {
        int temp = st.top();
        st.pop();
        SortedInsert(st, x);
        st.push(temp);
    }
}

void SortStack(stack<int> &st)
{
    if (st.empty())
        return;

    int temp = st.top();
    st.pop();
    SortStack(st);
    SortedInsert(st, temp);
}

int main()
{
    stack<int> st;
    st.push(5);
    st.push(2);
    st.push(3);
    st.push(4);
    st.push(1);
    SortStack(st);
    while (!st.empty())
    {
        cout << st.top() << " ";
        st.pop();
    }

    return 0;
}