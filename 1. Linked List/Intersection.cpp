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
        cout << temp->data << "->";
        temp = temp->next;
    }
    cout << "NULL" << endl;
}

int length(node *head)
{
    int l = 0;
    node *temp = head;
    while (temp != NULL)
    {
        l++;
        temp = temp->next;
    }
    return l;
}

void make_intersect(node *&head1, node *&head2, int pos)           // MAKE INTERSECT BETWEEN TWO LINKED LIST
{
    node *temp1 = head1;                                           // 10 -> 20 -> 30 -> 40 -> 50 -> 50 -> NULL
    pos--;                                                         // 45 -> NULL
                                                                    
    while (pos--)                                                  // After intersectionat position 5 head 5 is;
    {                                                              // 45 -> 50 -> 60 -> NULL
        temp1 = temp1->next;
    }

    node *temp2 = head2;
    while (temp2->next != NULL)
    {
        temp2 = temp2->next;
    }
    temp2->next = temp1;
}

int intersection(node *head1, node *head2)                       // DETECT INTERSECTION POINT BETWEEN TWO LINKED LIST
{
    int l1 = length(head1);
    int l2 = length(head2);
    int d = 0;
    node *ptr1, *ptr2;

    if (l1 > l2)
    {
        d = l1 - l2;
        ptr1 = head1;
        ptr2 = head2;
    }
    else
    {
        d = l2 - l1;
        ptr1 = head2;
        ptr2 = head1;
    }

    while (d--)
    {
        ptr1 = ptr1->next;
        if (ptr1 == NULL)
        {
            return -1;
        }
    }
    while (ptr1 != NULL && ptr2 != NULL)
    {
        if (ptr1 == ptr2)
        {
            return ptr1->data;
        }
        ptr1 = ptr1->next;
        ptr2 = ptr2->next;
    }
    return -1;
}

int main()
{
    node *head1 = NULL;
    insertattail(head1, 10);
    insertattail(head1, 20);
    insertattail(head1, 30);
    insertattail(head1, 40);
    insertattail(head1, 50);
    insertattail(head1, 60);
    display(head1);

    node *head2 = NULL;
    insertattail(head2, 45);

    display(head2);

    make_intersect(head1, head2, 5);
    display(head2);
    cout << intersection(head1, head2);

    return 0;
}
