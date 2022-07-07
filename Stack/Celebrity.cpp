#include <bits/stdc++.h>
using namespace std;

int Celebrity(vector<vector<int>> &relation, int n)
{
    stack<int> s;
    for (int i = 0; i < n; i++)
        s.push(i);

    while (s.size() > 1)
    {
        int a = s.top();
        s.pop();
        int b = s.top();
        s.pop();
        if (relation[a][b] == 1)
        {
            s.push(a);
        }
        else
        {
            s.push(b);
        }
    }
    int celeb = s.top();
    for (int i = 0; i < n; i++)
    {
        if (i != celeb && (!relation[celeb][i] || relation[i][celeb]))
            return -1;
    }
    return celeb;
}