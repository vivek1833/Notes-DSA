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

node *build_tree(vector<int> arr, int &i, int bound)
{
    if (i == arr.size() || arr[i] > bound)
        return NULL;
    node *root = new node(arr[i++]);
    root->left = build_tree(arr, i, root->data);
    root->right = build_tree(arr, i, bound);
    return root;
}

node *build_tree_from_preorder(vector<int> &arr)
{
    int i = 0;
    return build_tree(arr, i, INT_MAX);
}