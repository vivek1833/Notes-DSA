
#include <vector>
using namespace std;
bool canFinish(int n, vector<vector<int>>& pre){
    vector<vector<int>> g(n);
    for(auto &p: pre) g[p[1]].push_back(p[0]);
    vector<int> vis(n,0);
    function<bool(int)> dfs = [&](int u){
        vis[u]=1;
        for(int v: g[u]){
            if(vis[v]==1) return false;
            if(vis[v]==0 && !dfs(v)) return false;
        }
        vis[u]=2; return true;
    };
    for(int i=0;i<n;++i) if(vis[i]==0 && !dfs(i)) return false;
    return true;
}
