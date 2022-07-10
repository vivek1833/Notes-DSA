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

node *OddEven(node *&head)
{
    if (!head) // 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> NULL
    {          //  1 -> 3 -> 5 -> 2 -> 4 -> 6 -> NULL
        return NULL;
    }
    node *even = head->next;
    node *odd = head;
    node *evenhead = even;

    while (even && even->next)
    {
        odd->next = even->next;
        odd = odd->next;
        even->next = odd->next;
        even = even->next;
    }
    odd->next = evenhead;
    return head;
}