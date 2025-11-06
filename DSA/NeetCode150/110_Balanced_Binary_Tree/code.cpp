
int height(TreeNode* n){ if(!n) return 0; int l=height(n->left), r=height(n->right); if(l==-1||r==-1||abs(l-r)>1) return -1; return 1+max(l,r); }
bool isBalanced(TreeNode* root){ return height(root)!=-1; }
