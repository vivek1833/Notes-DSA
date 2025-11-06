
int ansLUP=0;
int dfsLUP(TreeNode* n){
    if(!n) return 0;
    int l=dfsLUP(n->left), r=dfsLUP(n->right), left=0, right=0;
    if(n->left && n->left->val==n->val) left = l+1;
    if(n->right && n->right->val==n->val) right = r+1;
    ansLUP = max(ansLUP, left+right);
    return max(left,right);
}
int longestUnivaluePath(TreeNode* root){ ansLUP=0; dfsLUP(root); return ansLUP; }
