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
// Catalain Number Formula: C(n) = C(0)C(n-1) + C(1)C(n-2) + ... + C(n-1)C(0)
// C(0) = 1, C(1) = 1

// Application of Catalan Number:
// Construct all BSTs with n node.
// Catalian Number for 3 is 5
// And possible BSTs with 3 nodes are: 5

int catalan_rec(int n) // USING RECURSION
{                      // Time Complexity: O(2^n)
    if (n <= 1)
        return 1;

    int sum = 0;
    for (int i = 0; i < n; i++)
        sum += catalan_rec(i) * catalan_rec(n - i - 1);

    return sum;
}

int catalan_dp(int n) // USING DP
{                     // Time Complexity: O(n)
    int dp[n + 1];
    dp[0] = 1;
    dp[1] = 1;

    for (int i = 2; i <= n; i++)
    {
        dp[i] = 0;
        for (int j = 0; j < i; j++)
            dp[i] += dp[j] * dp[i - j - 1];
    }

    return dp[n];
}

vector<node *> ConstTree(int st, int end)
{
    vector<node *> v; // Time Complexity: O(n)
    if (st > end)     // Construct all BSTs with n node.
    {
        v.push_back(NULL);
        return v;
    }
    for (int i = st; i <= end; i++)
    {
        vector<node *> left = ConstTree(st, i - 1);
        vector<node *> right = ConstTree(i + 1, end);
        for (int j = 0; j < left.size(); j++)
        {
            for (int k = 0; k < right.size(); k++)
            {
                node *temp = new node(i);
                temp->left = left[j];
                temp->right = right[k];
                v.push_back(temp);
            }
        }
    }
    return v;
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
    vector<node *> TotalTrees = ConstTree(1, 3);
    for (int i = 0; i < TotalTrees.size(); i++) // Tree 1 : 1 2 3
    {                                           // Tree 2 : 1 3 2
        cout << "Tree " << i + 1 << ": ";       // Tree 3 : 2 1 3
        preorder(TotalTrees[i]);                // Tree 4 : 3 1 2
        cout << endl;                           // Tree 5 : 3 2 1
    }
    return 0;
}
