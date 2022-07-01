#include <bits/stdc++.h>
using namespace std;

// A structure to represent a stack
class node
{
public:
    int data;
    node *next;
};

node *newNode(int data)
{
    node *stackNode = new node();
    stackNode->data = data;
    stackNode->next = NULL;
    return stackNode;
}

int isEmpty(node *root)
{
    return !root;
}

void push(node **root, int data)
{
    node *stackNode = newNode(data);
    stackNode->next = *root;
    *root = stackNode;
    cout << data << " pushed to stack\n";
}

int pop(node **root)
{
    if (isEmpty(*root))
        return INT_MIN;
        
    node *temp = *root;
    *root = (*root)->next;
    int popped = temp->data;
    free(temp);

    return popped;
}

int peek(node *root)
{
    if (isEmpty(root))
        return INT_MIN;
    return root->data;
}

int main()
{
    node *root = NULL;

    push(&root, 10);
    push(&root, 20);
    push(&root, 30);

    cout << pop(&root) << " popped from stack\n";

    cout << "Top element is " << peek(root) << endl;

    cout << "Elements present in stack : ";
    // print all elements
    while (!isEmpty(root))
    {
        // print top element in stack
        cout << peek(root) << " ";
        // remove top element in stack
        pop(&root);
    }

    return 0;
}