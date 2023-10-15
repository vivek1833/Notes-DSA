#include <bits/stdc++.h>
using namespace std;

queue<int> rev(queue<int> &q)
{
    stack<int> s;

    while (!q.empty())
    {
        int element = q.front();
        q.pop();
        s.push(element);
    }

    while (!s.empty())
    {
        int element = s.top();
        s.pop();
        q.push(element);
    }
    return q;
}

int main()
{
    queue<int> q;
    q.push(10);
    q.push(20);
    q.push(30); // 40 30 20 10
    q.push(40);

    rev(q);
    while (!q.empty())
    {
        cout << q.front() << " ";
        q.pop();
    }
    return 0;
}