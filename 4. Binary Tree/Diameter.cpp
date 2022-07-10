#include <bits/stdc++.h>
using namespace std;

struct node
{
    int data;
    struct node *left, *right;
};

int height(struct node *node)
{
    // base case tree is empty
    if (node == NULL)
        return 0;

    // If tree is not empty then height = 1 + max of left
    // height and right heights
    return 1 + max(height(node->right), height(node->left));
}

int diameter(struct node *tree)
{
    // base case where tree is empty
    if (tree == NULL)
        return 0;

    // get the height of left and right sub-trees
    int lheight = height(tree->left);
    int rheight = height(tree->right);

    // get the diameter of left and right sub-trees
    int ldiameter = diameter(tree->left);
    int rdiameter = diameter(tree->right);

    // Return max of following three
    // 1) Diameter of left subtree
    // 2) Diameter of right subtree
    // 3) Height of left subtree + height of right subtree + 1
    return max(lheight + rheight + 1,
               max(ldiameter, rdiameter));
}

// ANOTHER METHOD ON LEETCODE
int ans;
int count(node *node)
{
    if (!node)
        return 0;
    int x = count(node->left), y = count(node->right);
    ans = max(x + y, ans);
    return max(x, y) + 1;
}
int diameterOfBinaryTree(node *root)
{
    ans = 0;
    count(root);
    return ans;
}
