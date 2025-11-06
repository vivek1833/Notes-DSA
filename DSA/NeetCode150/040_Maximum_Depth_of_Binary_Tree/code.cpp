
// struct TreeNode { int val; TreeNode* left; TreeNode* right; };
int maxDepth(TreeNode* root){
    if(!root) return 0;
    return 1 + max(maxDepth(root->left), maxDepth(root->right));
}
