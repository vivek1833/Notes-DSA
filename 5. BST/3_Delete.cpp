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

void Delete(node *&root, int val)
{
    if (root == NULL)
        return;

    if (val < root->data)
        Delete(root->left, val);

    else if (val > root->data)
        Delete(root->right, val);

    else
    {
        if (root->left == NULL && root->right == NULL)
            root = NULL;

        else if (root->left == NULL)
            root = root->right;

        else if (root->right == NULL)
            root = root->left;

        else
        {
            node *temp = root->right;
            while (temp->left != NULL)
                temp = temp->left;
            root->data = temp->data;
            Delete(root->right, temp->data);
        }
    }
}