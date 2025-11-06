
#include <vector>
#include <string>
using namespace std;
vector<string> resBTP; string curBTP;
void dfsBTP(TreeNode* n){
    if(!n) return;
    int len=curBTP.size();
    if(!curBTP.empty()) curBTP += "->";
    curBTP += to_string(n->val);
    if(!n->left && !n->right) resBTP.push_back(curBTP);
    dfsBTP(n->left); dfsBTP(n->right);
    curBTP.resize(len);
}
vector<string> binaryTreePaths(TreeNode* root){ resBTP.clear(); curBTP=""; dfsBTP(root); return resBTP; }
