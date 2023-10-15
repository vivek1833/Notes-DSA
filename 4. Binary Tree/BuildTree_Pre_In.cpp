#include <bits/stdc++.h>
using namespace std;

struct node
{
    int data;
    node *left = NULL;
    node *right = NULL;

    node(int val)
    {
        data = val;
        left = right = NULL;
    }
};

int searchInOrder(int InOrder[], int start, int end, int curr)
{
    for (int j = start; j <= end; j++)
    {
        if (InOrder[j] == curr)
        {             // if current element in inOrder
            return j; // return it's index
        }
    }
    return -1; // which is not possible.
}

node *BuildTree(int InOrder[], int PreOrder[], int start, int end)
{
    if (start > end)
    {
        return NULL;
    }
    static int i = 0;
    int curr = PreOrder[i];      // 1. select the current element
    node *root = new node(curr); // 1(2).create a new node
    i++;                         // 2. Increment the index
    if (start == end)
    {
        return root; // means only 1 node in our inorder.
    }
    int pos = searchInOrder(InOrder, start, end, curr);
    // 3. search for its position in InOrder
    root->left = BuildTree(InOrder, PreOrder, start, pos - 1);
    // 4. Build using the left subarray of InOrder
    root->right = BuildTree(InOrder, PreOrder, pos + 1, end);
    // 5. Build using the right subarray of InOrder
    return root;
}

void preorder(struct node *root)
{
    if (root == NULL)
    {
        return;
    }

    cout << root->data << " ";
    preorder(root->left);
    preorder(root->right);
}

int main()
{
    int InOrder[] = {4, 2, 5, 1, 3};
    int PreOrder[] = {1, 2, 4, 5, 3};
    int n = sizeof(InOrder) / sizeof(InOrder[0]);
    node *root = BuildTree(InOrder, PreOrder, 0, n - 1);
    preorder(root);
    return 0;
}