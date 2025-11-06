
// struct TreeNode{ int val; TreeNode* left; TreeNode* right; };
bool isSym(TreeNode* a, TreeNode* b){ if(!a||!b) return a==b; return a->val==b->val && isSym(a->left,b->right) && isSym(a->right,b->left); }
bool isSymmetric(TreeNode* root){ if(!root) return true; return isSym(root->left, root->right); }
