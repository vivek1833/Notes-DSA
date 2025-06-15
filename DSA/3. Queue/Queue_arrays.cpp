#include <iostream>
using namespace std;
#define n 100

class queue
{
    char *arr;
    int front;
    int back;

public:
    queue()
    {
        arr = new char[n];
        front = -1;
        back = -1;
    }
    void push(char x)
    {
        if (back == n - 1)
        {
            cout << "Queue overflow..." << endl;
            return;
        }
        back++;
        arr[back] = x;
        if (front == -1)
        {
            front++;
        }
    }

    void pop()
    {
        if (front == -1 || front > back)
        {
            cout << "No elements..." << endl;
            return;
        }
        front++;
    }
    char peek()
    {
        if (front == -1 || front > back)
        {
            cout << "No elements..." << endl;
            return -1;
        }
        return arr[front];
    }
    bool empty()
    {
        if (front == -1 || front > back)
        {
            return true;
        }
        return false;
    }
};

int main()
{
    queue q;
    q.push('A');
    q.push('B');
    q.push('C');
    q.push('D');
    q.push('E');
    q.push('F');

    q.pop();
    q.pop();

    q.push('G');
    q.push('H');

    q.pop();
    q.pop();
    q.pop();
    q.pop();

    q.push('I');

    cout << q.peek();
    return 0;
}