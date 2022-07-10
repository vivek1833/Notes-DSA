#include <bits/stdc++.h>
using namespace std;

class Queue
{
    stack<int> s1, s2;

public:
    void enqueue(int data)
    {
        s1.push(data);
    }

    int dequeue()
    {
        // if both stacks are empty
        if (s1.empty() && s2.empty())
        {
            cout << "Underflow!!";
            exit(0);
        }

        if (s2.empty())
        {
            while (!s1.empty())
            {
                s2.push(s1.top());
                s1.pop();
            }
        }

        int top = s2.top();
        s2.pop();
        return top;
    }

    int peek()
    {
        // if both stacks are empty
        if (s1.empty() && s2.empty())
        {
            cout << "Underflow!!";
            exit(0);
        }

        if (s2.empty())
        {
            while (!s1.empty())
            {
                s2.push(s1.top());
                s1.pop();
            }
        }

        return s2.top();
    }

    int size()
    {
        return s1.size() + s2.size();
    }
};

int main()
{
    Queue q;
    q.enqueue(1);
    q.enqueue(2);
    q.enqueue(3);
    q.enqueue(4);
    q.enqueue(5);

    cout << q.dequeue() << endl; // print 1
    cout << q.dequeue() << endl; // print 2
    cout << q.peek() << endl;    // print 3
    cout << q.size() << endl;    // print 3

    return 0;
}
