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

node* BalancedBST(int arr[], int start, int end)
{
    if (start > end)
        return NULL;
    
    int mid = (start + end) / 2;
    node *root = new node(arr[mid]);
    root->left = BalancedBST(arr, start, mid - 1);
    root->right = BalancedBST(arr, mid + 1, end);
    return root;
}