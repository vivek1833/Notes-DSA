
#include <vector>
using namespace std;
bool validPath(int n, vector<vector<int>>& edges, int s, int t){
    vector<vector<int>> g(n);
    for(auto &e: edges){ g[e[0]].push_back(e[1]); g[e[1]].push_back(e[0]); }
    vector<int> vis(n,0);
    function<bool(int)> dfs = [&](int u){
        if(u==t) return true; vis[u]=1;
        for(int v: g[u]) if(!vis[v] && dfs(v)) return true;
        return false;
    };
    return dfs(s);
}
