
// struct TreeNode { int val; TreeNode* left; TreeNode* right; };
bool valid(TreeNode* n, long long lo, long long hi){
    if(!n) return true;
    if(n->val<=lo || n->val>=hi) return false;
    return valid(n->left, lo, n->val) && valid(n->right, n->val, hi);
}
bool isValidBST(TreeNode* root){ return valid(root, LLONG_MIN, LLONG_MAX); }
