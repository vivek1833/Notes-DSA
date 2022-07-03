#include <iostream>
using namespace std;

struct node
{
    int data;
    node *next;
    node(int val)
    {
        data = val;
        next = NULL;
    }
};

// top pointer to keep track of the top of the stack
node *top = NULL;

// Function to check if stack is empty or not
bool isempty()
{
    if (top == NULL)
        return true;
    else
        return false;
}

// Function to insert an element in stack
void push(int value)
{
    node *ptr = new node(value);
    ptr->next = top;
    top = ptr;
}

// Function to delete an element from the stack
void pop()
{
    if (isempty())
        cout << "Stack is Empty";
    else
    {
        node *ptr = top;
        top = top->next;
        delete (ptr);
    }
}

// Function to show the element at the top of the stack
void showTop()
{
    if (isempty())
        cout << "Stack is Empty";
    else
        cout << "Element at top is : " << top->data;
}

// Function to Display the stack
void displayStack()
{
    if (isempty())
        cout << "Stack is Empty";
    else
    {
        node *temp = top;
        while (temp != NULL)
        {
            cout << temp->data << " ";
            temp = temp->next;
        }
        cout << "\n";
    }
}

int main()
{
    node *head = NULL;
    push(10);
    push(20);
    push(30);
    push(40);
    push(50);

    displayStack();
    pop();
    displayStack();

    return 0;
}