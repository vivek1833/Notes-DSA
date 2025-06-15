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

int maxPathsum(Node *root)
{
    if (root == NULL)
    {
        return 0;
    }
    int ans = INT_MIN; // -Infinity

    int a = root->data;
    int b = maxPathsum(root->left) + root->data;
    int c = maxPathsum(root->right) + root->data;
    int d = maxPathsum(root->left) + maxPathsum(root->right) + root->data;

    int temp = max(max(a, b), max(c, d));
    ans = max(ans, temp);

    return ans;
}
int main()
{

    struct Node *root = new Node(1);
    root->left = new Node(-2);
    root->right = new Node(4);
    root->left->left = new Node(4);
    root->right->right = new Node(-5);
    cout << maxPathsum(root);
    return 0;
}