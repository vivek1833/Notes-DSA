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

bool Search(node *root, int val)
{
    if (root == NULL)
        return false;

    if (root->data == val)
        return true;

    if (val < root->data)
        return Search(root->left, val);

    return Search(root->right, val);
}