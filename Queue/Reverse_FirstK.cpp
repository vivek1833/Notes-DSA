#include <bits/stdc++.h>
using namespace std;

queue<int> modifyQueue(queue<int> q, int k)
{
    stack<int> s;

    for (int i = 0; i < k; i++)
    {
        int val = q.front();
        q.pop();
        s.push(val);
    }

    while (!s.empty())
    {
        int val = s.top();
        s.pop();
        q.push(val);
    }

    int t = q.size() - k;

    while (t--)
    {
        int val = q.front();
        q.pop();
        q.push(val);
    }

    return q;
}

int main()
{
    queue<int> q;
    q.push(10);
    q.push(20);
    q.push(30); // 30 20 10 40 50 60 70
    q.push(40);
    q.push(50);
    q.push(60);
    q.push(70);

    int k = 3;

    queue<int> ans = modifyQueue(q, k);
    while (!ans.empty())
    {
        int a = ans.front();
        ans.pop();
        cout << a << " ";
    }
    cout << endl;
}
