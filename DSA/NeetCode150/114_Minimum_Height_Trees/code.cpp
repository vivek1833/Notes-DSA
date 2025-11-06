
#include <vector>
#include <queue>
using namespace std;
vector<int> findMinHeightTrees(int n, vector<vector<int>>& edges){
    if(n==1) return {0};
    vector<vector<int>> g(n);
    vector<int> deg(n,0);
    for(auto&e:edges){ g[e[0]].push_back(e[1]); g[e[1]].push_back(e[0]); deg[e[0]]++; deg[e[1]]++; }
    queue<int> q; for(int i=0;i<n;++i) if(deg[i]==1) q.push(i);
    int remaining=n;
    while(remaining>2){
        int sz=q.size(); remaining-=sz;
        while(sz--){
            int u=q.front(); q.pop();
            for(int v: g[u]) if(--deg[v]==1) q.push(v);
        }
    }
    vector<int> res; while(!q.empty()){ res.push_back(q.front()); q.pop(); } return res;
}
