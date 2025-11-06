
#include <unordered_map>
#include <vector>
using namespace std;
// class Node { public: int val; vector<Node*> neighbors; Node(): val(0), neighbors({}) {} Node(int _val): val(_val), neighbors({}) {} };
Node* dfsCG(Node* n, unordered_map<int,Node*>& mp){
    if(!n) return nullptr;
    if(mp.count(n->val)) return mp[n->val];
    Node* c=new Node(n->val); mp[n->val]=c;
    for(auto nei: n->neighbors) c->neighbors.push_back(dfsCG(nei, mp));
    return c;
}
Node* cloneGraph(Node* node){
    unordered_map<int,Node*> mp; return dfsCG(node, mp);
}
