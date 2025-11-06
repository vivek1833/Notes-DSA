
// struct TreeNode { int val; TreeNode* left; TreeNode* right; };
int dfs(TreeNode* n, int &ans){
    if(!n) return 0;
    int l=max(0, dfs(n->left, ans));
    int r=max(0, dfs(n->right, ans));
    ans=max(ans, n->val + l + r);
    return n->val + max(l,r);
}
int maxPathSum(TreeNode* root){
    int ans=INT_MIN; dfs(root, ans); return ans;
}
