
#include <vector>
using namespace std;
int prevVal=INT_MIN, curCount=0, maxCount=0;
vector<int> modes;
void handleVal(int v){
    if(v!=prevVal){ prevVal=v; curCount=1; }
    else curCount++;
    if(curCount>maxCount){ maxCount=curCount; modes.clear(); modes.push_back(v); }
    else if(curCount==maxCount) modes.push_back(v);
}
void inorderMode(TreeNode* n){
    if(!n) return;
    inorderMode(n->left);
    handleVal(n->val);
    inorderMode(n->right);
}
vector<int> findMode(TreeNode* root){ modes.clear(); prevVal=INT_MIN; curCount=0; maxCount=0; inorderMode(root); return modes; }
