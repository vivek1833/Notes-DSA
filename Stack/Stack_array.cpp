#include <bits/stdc++.h>
using namespace std;

#define MAX 1000

class Stack
{
    int top;

public:
    int a[MAX];

    Stack()
    {
        top = -1;
    }
    void push(int x);
    void pop();
    int peek();
    bool isEmpty();
};

void Stack::push(int x)
{
    if (top >= (MAX - 1))
    {
        cout << "Stack Overflow";
        return;
    }
    else
    {
        a[++top] = x;
        return;
    }
}

void Stack::pop()
{
    if (top < 0)
    {
        cout << "Stack Underflow";
        return;
    }
    else
    {
        int x = a[top--];
        return;
    }
}

int Stack::peek()
{
    if (top < 0)
    {
        cout << "Stack is Empty";
        return 0;
    }
    else
    {
        int x = a[top];
        return x;
    }
}

bool Stack::isEmpty()
{
    return (top < 0);
}

int main()
{
    class Stack s;
    s.push(10);
    s.push(20);
    s.push(30);
    s.pop();

    // print top element of stack after poping
    cout << "Top element is : " << s.peek() << endl;

    // print all elements in stack :
    cout << "Elements present in stack : ";
    while (!s.isEmpty())
    {
        // print top element in stack
        cout << s.peek() << " ";
        // remove top element in stack
        s.pop();
    }

    return 0;
}