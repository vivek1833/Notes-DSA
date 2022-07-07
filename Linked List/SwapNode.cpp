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

node *swapNodes(node *head, int k)
{
    node *slow = head, *fast = head, *n1 = head;
    // finding n1
    for (int i = 0; i < k - 1; i++)
    {
        fast = fast->next;
        n1 = fast;
    }

    // finding n2 (i.e slow)
    while (fast->next != NULL)
    {
        fast = fast->next;
        slow = slow->next;
    }

    // swapping
    int n1_val = n1->data;
    n1->data = slow->data;
    slow->data = n1_val;
    return head;
    // 1 -> 2 -> 3 -> 4 -> 5 -> NULL  k = 2
    // Swaps k position from start and k position from end.
    // 1 -> 4 -> 3 -> 2 -> 5 -> NULL
};
