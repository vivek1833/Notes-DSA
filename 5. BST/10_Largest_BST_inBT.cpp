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

struct Info
{
    int sz;     // Size of subtree
    int max;    // Min value in subtree
    int min;    // Max value in subtree
    int ans;    // Size of largest BST which
    bool isBST; // If subtree is BST
};

Info largestBSTBT(node *root)
{

    if (root == NULL)
        return {0, INT_MIN, INT_MAX, 0, true};

    if (root->left == NULL && root->right == NULL)
        return {1, root->data, root->data, 1, true};

    // Recur for left subtree and right subtrees
    Info l = largestBSTBT(root->left);
    Info r = largestBSTBT(root->right);

    // Create a return variable and initialize its size.
    Info ret;
    ret.sz = (1 + l.sz + r.sz);

    // If whole tree rooted under current root is BST.
    if (l.isBST && r.isBST && l.max < root->data &&
        r.min > root->data)
    {
        ret.min = min(l.min, min(r.min, root->data));
        ret.max = max(r.max, max(l.max, root->data));

        // Update answer for tree rooted under current 'root'
        ret.ans = max(l.ans, r.ans) + 1;
        ret.isBST = true;

        return ret;
    }

    // If whole tree is not BST, return maximum of left and right subtrees
    ret.ans = max(l.ans, r.ans);
    ret.isBST = false;

    return ret;
}

int main()
{
    node *root = NULL;
    root = new node(60);
    root->left = new node(65);
    root->right = new node(70);
    root->left->left = new node(50);
    cout << largestBSTBT(root).ans; // 2
    return 0;
}