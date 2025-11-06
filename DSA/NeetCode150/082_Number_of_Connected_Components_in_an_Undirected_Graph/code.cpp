
#include <vector>
using namespace std;
int countComponents(int n, vector<vector<int>>& edges){
    vector<vector<int>> g(n);
    for(auto &e: edges){ g[e[0]].push_back(e[1]); g[e[1]].push_back(e[0]); }
    vector<int> vis(n,0); int cnt=0;
    function<void(int)> dfs = [&](int u){ vis[u]=1; for(int v: g[u]) if(!vis[v]) dfs(v); };
    for(int i=0;i<n;++i) if(!vis[i]){ ++cnt; dfs(i); }
    return cnt;
}
