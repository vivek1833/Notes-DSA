
int sumNumbers(TreeNode* root){
    function<int(TreeNode*, int)> dfs = [&](TreeNode* n, int cur){
        if(!n) return 0;
        cur = cur*10 + n->val;
        if(!n->left && !n->right) return cur;
        return dfs(n->left, cur) + dfs(n->right, cur);
    };
    return dfs(root,0);
}
