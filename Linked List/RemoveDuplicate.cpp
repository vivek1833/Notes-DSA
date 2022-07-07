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

node *deleteDuplicates_1(node *head)
{
    node *dummy = new node(101);
    dummy->next = head;
    node *curr = head;
    node *prev = dummy;
    while (curr != NULL)
    {
        if (curr->data == prev->data)
        {
            prev->next = curr->next;
            curr = curr->next;
        }
        else
        {
            prev = curr;
            curr = curr->next;
        }
    }
    return dummy->next;
}

// Another approach
node *deleteDuplicates_2(node *head)
{
    if (head == NULL || head->next == NULL)
        return head;
    node *curr = head;
    while (curr->next != NULL)
    {
        if (curr->data == curr->next->data)
        {
            curr->next = curr->next->next;
        }
        else
        {
            curr = curr->next;
        }
    }
    return head;
}