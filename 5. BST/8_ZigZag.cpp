#include <bits/stdc++.h>
using namespace std;

struct node
{
    int data;
    node *left;
    node *right;
    node(int val)
    {
        data = val;
        left = NULL;
        right = NULL;
    }
};

node *Insert(node *root, int val)
{
    if (root == NULL)
        return new node(val);

    if (val < root->data)
        root->left = Insert(root->left, val);

    else
        root->right = Insert(root->right, val);

    return root;
}

void Zigzag(node *root)
{
    if (root == NULL)
        return;
    queue<node *> q;
    q.push(root);
    bool flag = false;
    while (!q.empty())
    {
        int size = q.size();
        for (int i = 0; i < size; i++)
        {
            node *temp = q.front();
            q.pop();
            if (flag)
            {
                cout << temp->data << " ";
            }
            else
            {
                cout << temp->data << " ";
            }
            if (temp->left)
            {
                q.push(temp->left);
            }
            if (temp->right)
            {
                q.push(temp->right);
            }
        }
        flag = !flag;
    }
}

int main()
{
    node *root = NULL;
    root = Insert(root, 10);
    Insert(root, 7);
    Insert(root, 8);
    Insert(root, 4);
    Insert(root, 5);

    Zigzag(root); // 10 7 4 8 5

    return 0;
}
