
#include <vector>
using namespace std;
vector<int> findOrder(int n, vector<vector<int>>& pre){
    vector<vector<int>> g(n);
    vector<int> indeg(n,0);
    for(auto &p: pre){ g[p[1]].push_back(p[0]); indeg[p[0]]++; }
    vector<int> q, res;
    for(int i=0;i<n;++i) if(indeg[i]==0) q.push_back(i);
    for(int i=0;i<(int)q.size(); ++i){
        int u=q[i]; res.push_back(u);
        for(int v: g[u]) if(--indeg[v]==0) q.push_back(v);
    }
    return (int)res.size()==n? res : vector<int>{};
}
