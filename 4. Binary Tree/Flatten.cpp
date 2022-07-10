#include <bits/stdc++.h>
using namespace std;
struct Node
{
    int data;
    struct Node *left;
    struct Node *right;
    Node(int val)
    {
        data = val;
        left = NULL;
        right = NULL;
    }
};

void flatten(Node *root)
{
    if (root == NULL or (root->left == NULL and root->right == NULL))
        return;
    if (root->left != NULL)
    {
        flatten(root->left);

        Node *temp = root->right;
        root->right = root->left;
        root->left = NULL;
        Node *t = root->right;
        while (t->right != NULL)
        {
            t = t->right;
        }
        t->right = temp;
    }
    flatten(root->right);
}

void inorderprint(Node *root)
{
    if (root == NULL)
        return;
    inorderprint(root->left);
    cout << root->data << " ";
    inorderprint(root->right);
}
int main()
{
    /*    1
         / \
        2   3
       / \
      4   5
    */

    Node *root = new Node(1);
    root->left = new Node(2);
    root->right = new Node(3);
    root->left->left = new Node(4);
    root->left->right = new Node(5);
    flatten(root);
    inorderprint(root);
    return 0;
}