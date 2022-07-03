#include <bits/stdc++.h>
using namespace std;

int main()
{
    string s = "Hello! what are you doing?";

    stack<string> st;
    string words = "";
    for (int i = 0; i < s.size(); i++)
    {
        if (s[i] != ' ')
            words = words + s[i];
        else
        {
            st.push(words);
            words = "";
        }
    }

    st.push(words);

    while (!st.empty())
    {

        cout << st.top() << " ";
        st.pop();
    }

    return 0;
}