
#include <vector>
#include <string>
using namespace std;
vector<vector<int>> resPS2; vector<int> curPS2;
void dfsPS2(TreeNode* node, int sum){
    if(!node) return;
    curPS2.push_back(node->val);
    if(!node->left && !node->right && sum==node->val) resPS2.push_back(curPS2);
    dfsPS2(node->left, sum-node->val); dfsPS2(node->right, sum-node->val);
    curPS2.pop_back();
}
vector<vector<int>> pathSum(TreeNode* root, int sum){ resPS2.clear(); dfsPS2(root,sum); return resPS2; }
