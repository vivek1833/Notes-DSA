
#include <vector>
#include <unordered_map>
using namespace std;
// struct TreeNode { int val; TreeNode* left; TreeNode* right; TreeNode(int x):val(x),left(nullptr),right(nullptr){} };
TreeNode* build(vector<int>& pre, int ps, int pe, vector<int>& in, int is, int ie, unordered_map<int,int>& pos){
    if(ps>pe||is>ie) return nullptr;
    int val=pre[ps], mid=pos[val], left=mid-is;
    TreeNode* root=new TreeNode(val);
    root->left = build(pre, ps+1, ps+left, in, is, mid-1, pos);
    root->right= build(pre, ps+left+1, pe, in, mid+1, ie, pos);
    return root;
}
TreeNode* buildTree(vector<int>& preorder, vector<int>& inorder){
    unordered_map<int,int> pos; for(int i=0;i<(int)inorder.size();++i) pos[inorder[i]]=i;
    return build(preorder,0,(int)preorder.size()-1,inorder,0,(int)inorder.size()-1,pos);
}
