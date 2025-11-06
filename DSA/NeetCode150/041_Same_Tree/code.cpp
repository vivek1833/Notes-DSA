
// struct TreeNode { int val; TreeNode* left; TreeNode* right; };
bool isSameTree(TreeNode* p, TreeNode* q){
    if(!p||!q) return p==q;
    return p->val==q->val && isSameTree(p->left,q->left) && isSameTree(p->right,q->right);
}
