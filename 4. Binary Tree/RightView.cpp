#include <bits/stdc++.h>
using namespace std;

struct Node
{
    int data;
    struct Node *left, *right;
    Node(int val)
    {
        data = val;
        left = right = NULL;
    }
};

struct Node *newNode(int item)
{
    struct Node *temp = new Node(item);
    return temp;
}

void rightViewUtil(struct Node *root, int level, int *max_level)
{
    // Base Case
    if (root == NULL)
        return;

    // If this is the last Node of its level
    if (*max_level < level)
    {
        cout << root->data << "\t";
        *max_level = level;
    }

    // Recur for right subtree first,
    // then left subtree
    rightViewUtil(root->right, level + 1, max_level);
    rightViewUtil(root->left, level + 1, max_level);
}

// A wrapper over rightViewUtil()
void rightView(struct Node *root)
{
    int max_level = 0;
    rightViewUtil(root, 1, &max_level);
}

void printRightView(Node *root)     // USING QUEUE
{
    if (root == NULL)
        return;

    queue<Node *> q;
    q.push(root);

    while (!q.empty())
    {
        // get number of nodes for each level
        int n = q.size();

        // traverse all the nodes of the current level
        while (n--)
        {

            Node *x = q.front();
            q.pop();

            // print the last node of each level
            if (n == 0)
            {
                cout << x->data << " ";
            }
            // if left child is not null push it into the queue
            if (x->left)
                q.push(x->left);
            // if right child is not null push it into the queue
            if (x->right)
                q.push(x->right);
        }
    }
}

int main()
{
    struct Node *root = newNode(1);
    root->left = newNode(2);
    root->right = newNode(3);
    root->left->left = newNode(4);
    root->left->right = newNode(5);
    root->right->left = newNode(6);
    root->right->right = newNode(7);
    root->right->right->right = newNode(8);

    rightView(root);

    return 0;
}
