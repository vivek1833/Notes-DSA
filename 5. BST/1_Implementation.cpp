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

// Creating a BST
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

void inorder(node *root)
{
    if (root == NULL)
        return;

    inorder(root->left);
    cout << root->data << " ";
    inorder(root->right);
}

int main()
{
    node *root = NULL;
    root = Insert(root, 10);
    Insert(root, 7);
    Insert(root, 8);
    Insert(root, 4);
    Insert(root, 5);

    inorder(root); // 4 5 7 8 10
    return 0;
}
