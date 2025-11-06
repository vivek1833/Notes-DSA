
// struct TreeNode { int val; TreeNode* left; TreeNode* right; };
int kthSmallest(TreeNode* root, int k){
    vector<int> st;
    TreeNode* cur=root;
    while(cur || !st.empty()){
        while(cur){ st.push_back((long long)cur); cur=cur->left; }
        cur=(TreeNode*)st.back(); st.pop_back();
        if(--k==0) return cur->val;
        cur=cur->right;
    }
    return -1;
}
