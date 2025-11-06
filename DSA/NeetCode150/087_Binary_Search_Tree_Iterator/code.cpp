
#include <stack>
using namespace std;
// struct TreeNode{ int val; TreeNode* left; TreeNode* right; };
class BSTIterator {
    stack<TreeNode*> st;
    void pushLeft(TreeNode* n){ while(n){ st.push(n); n=n->left; } }
public:
    BSTIterator(TreeNode* root){ pushLeft(root); }
    int next(){ TreeNode* n = st.top(); st.pop(); pushLeft(n->right); return n->val; }
    bool hasNext(){ return !st.empty(); }
};
