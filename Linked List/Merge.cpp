#include <bits/stdc++.h>
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

void insertathead(node *&head, int val)
{
    node *n = new node(val);
    n->next = head;
    head = n;
}

void insertattail(node *&head, int val)
{
    node *n = new node(val);
    if (head == NULL)
    {
        head = n;
        return;
    }
    node *temp = head;
    while (temp->next != NULL)
    {
        temp = temp->next;
    }
    temp->next = n;
}

void display(node *head)
{
    node *temp = head;
    while (temp != NULL)
    {
        cout << temp->data << " -> ";
        temp = temp->next;
    }
    cout << "NULL" << endl;
}

node *merge_recur(node *&head1, node *&head2)
{
    if (head1 == NULL)
        return head2;

    if (head2 == NULL)
        return head1;

    node *result;
    if (head1->data < head2->data)
    {
        result = head1;
        result->next = merge_recur(head1->next, head2);
    }
    else
    {
        result = head2;
        result->next = merge_recur(head1, head2->next);
    }

    return result;
}

node *merge_iter(node *&head1, node *&head2)
{
    node *p1 = head1;
    node *p2 = head2;
    node *dummynode = new node(-1);
    node *p3 = dummynode;

    while (p1 != NULL && p2 != NULL)
    {
        if (p1->data < p2->data)
        {
            p3->next = p1;
            p1 = p1->next;
        }
        else
        {
            p3->next = p2;
            p2 = p2->next;
        }
        p3 = p3->next;
    }

    while (p1 != NULL)
    {
        p3->next = p1;
        p1 = p1->next;
        p3 = p3->next;
    }
    while (p2 != NULL)
    {
        p3->next = p2;
        p2 = p2->next;
        p3 = p3->next;
    }

    return (dummynode->next);
}

int main()
{
    node *head1 = NULL;
    insertathead(head1, 40);
    insertathead(head1, 30);
    insertathead(head1, 20);
    insertathead(head1, 10);
    display(head1);

    node *head2 = NULL;
    insertathead(head2, 45);
    insertathead(head2, 35);
    insertathead(head2, 25);
    insertathead(head2, 5);
    display(head2);

    node *newnode = merge_recur(head1, head2);
    node *newnode = merge_iter(head1, head2);

    display(newnode);

    return 0;
}
