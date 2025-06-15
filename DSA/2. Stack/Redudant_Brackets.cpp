#include <bits/stdc++.h>
using namespace std;

bool RedBrac(string &s)
{
    stack<char> st;
    for (int i = 0; i < s.length(); i++)
    {
        char ch = s[i];
        if (ch == '(' || ch == '+' || ch == '-' || ch == '*' || ch == '/')
        {
            st.push(ch);
        }
        else
        {
            if (ch == ')')
            {
                bool isRed = true;
                while (st.top() != '(')
                {
                    if (st.top() == '+' || st.top() == '-' || st.top() == '*' || st.top() == '/')
                    {
                        isRed = false;
                    }
                    st.pop();
                }
                if (isRed)
                {
                    return true;
                }
                st.pop();
            }
        }
    }
    return false;
}

int main()
{
    string str = "((a+b))"; // 1
    cout << RedBrac(str) << endl;

    str = "(a+(b)/c)"; // 1
    cout << RedBrac(str) << endl;

    str = "(a+b*(c-d))"; // 0
    cout << RedBrac(str) << endl;
    return 0;
}