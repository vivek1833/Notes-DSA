
#include <vector>
#include <deque>
using namespace std;
// struct TreeNode{ int val; TreeNode* left; TreeNode* right; };
vector<vector<int>> zigzagLevelOrder(TreeNode* root){
    vector<vector<int>> res; if(!root) return res;
    deque<TreeNode*> dq; dq.push_back(root); bool leftToRight=true;
    while(!dq.empty()){
        int n=dq.size(); res.emplace_back();
        for(int i=0;i<n;++i){
            if(leftToRight){
                TreeNode* node=dq.front(); dq.pop_front();
                res.back().push_back(node->val);
                if(node->left) dq.push_back(node->left);
                if(node->right) dq.push_back(node->right);
            } else {
                TreeNode* node=dq.back(); dq.pop_back();
                res.back().push_back(node->val);
                if(node->right) dq.push_front(node->right);
                if(node->left) dq.push_front(node->left);
            }
        }
        leftToRight=!leftToRight;
    }
    return res;
}
