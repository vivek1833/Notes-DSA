
#include <vector>
using namespace std;
int findCircleNum(vector<vector<int>>& isConnected){
    int n=isConnected.size(), cnt=0;
    vector<int> vis(n,0);
    function<void(int)> dfs = [&](int u){ vis[u]=1; for(int v=0; v<n; ++v) if(isConnected[u][v] && !vis[v]) dfs(v); };
    for(int i=0;i<n;++i) if(!vis[i]){ ++cnt; dfs(i); }
    return cnt;
}
