#include <iostream>
using namespace std;

class node
{
public:
    int data;
    node *next;
    node(int val)
    {
        data = val;
        next = NULL;
    }
};

class queue
{
    node *front;
    node *back;

public:
    queue()
    {
        front = NULL;
        back = NULL;
    }

    void push(int x)
    {
        node *n = new node(x);
        if (front == NULL)
        {
            back = n;
            front = n;
            return;
        }
        back->next = n;
        back = n;
    }

    void pop()
    {
        if (front == NULL)
        {
            cout << "Queue underflow..." << endl;
            return;
        }
        node *todelete = front;
        front = front->next;
        delete todelete;
    }

    int peek()
    {
        if (front == NULL)
        {
            cout << "No element in queue..." << endl;
            return -1;
        }
        return front->data;
    }

    void empty()
    {
        if (front == NULL)
        {
            cout << "Empty queue...";
        }
        else
            cout << "Non empty queue...";
    }
};

int main()
{
    queue q;
    cout << "Inserting element in queue...";
    q.push(10);
    q.push(20);
    q.push(30);
    cout << "Element at top on queue...";
    cout << q.peek() << endl;
    cout << "Deleting element from queue...";
    q.pop();
    q.pop();
    q.pop();
    q.pop();
    cout << "Check if queue is empty...";
    q.empty();
    return 0;
}