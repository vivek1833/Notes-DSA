
#include <string>
#include <queue>
using namespace std;
// struct TreeNode{ int val; TreeNode* left; TreeNode* right; };
string serialize(TreeNode* root){
    if(!root) return "";
    string s; queue<TreeNode*> q; q.push(root);
    while(!q.empty()){
        auto n=q.front(); q.pop();
        if(n){ s+=to_string(n->val)+","; q.push(n->left); q.push(n->right); }
        else s+="#,";
    }
    return s;
}
TreeNode* deserialize(string data){
    if(data.empty()) return nullptr;
    vector<string> vals; string cur;
    for(char c: data){ if(c==','){ vals.push_back(cur); cur.clear(); } else cur+=c; }
    int i=0;
    TreeNode* root = new TreeNode(stoi(vals[i++]));
    queue<TreeNode*> q; q.push(root);
    while(!q.empty() && i<(int)vals.size()){
        auto n=q.front(); q.pop();
        if(vals[i]!="#"){ n->left=new TreeNode(stoi(vals[i])); q.push(n->left); } ++i;
        if(i<(int)vals.size() && vals[i]!="#"){ n->right=new TreeNode(stoi(vals[i])); q.push(n->right); } ++i;
    }
    return root;
}
