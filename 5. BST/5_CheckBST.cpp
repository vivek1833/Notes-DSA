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

bool isBST(node *root)
{
    if (root == NULL)
        return true;
    if (root->left != NULL && root->left->data > root->data)
        return false;
    if (root->right != NULL && root->right->data < root->data)
        return false;
    if (!isBST(root->left) || !isBST(root->right))
        return false;
    return true;
}