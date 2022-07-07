#include <bits/stdc++.h>
using namespace std;

bool IsValid(string s)
{
    stack<char> st;
    for (auto i : s)
    {
        if (i == '(' || i == '[' || i == '{')
            st.push(i);
        else
        {
            if ((i == ')' && st.top() != '(') || (i == ']' && st.top() != '[') || (i == '}' && st.top() != '{'))
                return false;

            st.pop();
        }
    }
    return st.empty();
}

int main()
{
    string s = "{[()]}"; // true
    cout << IsValid(s) << endl;
}
