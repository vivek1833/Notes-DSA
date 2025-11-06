
// struct TreeNode { int val; TreeNode* left; TreeNode* right; };
TreeNode* lowestCommonAncestor(TreeNode* root, TreeNode* p, TreeNode* q){
    if(!root || root==p || root==q) return root;
    TreeNode* L=lowestCommonAncestor(root->left,p,q);
    TreeNode* R=lowestCommonAncestor(root->right,p,q);
    if(L && R) return root;
    return L? L : R;
}
