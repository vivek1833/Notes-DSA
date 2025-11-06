
#include <vector>
#include <queue>
using namespace std;
int findCheapestPrice(int n, vector<vector<int>>& flights, int src, int dst, int K){
    vector<vector<pair<int,int>>> g(n);
    for(auto &f: flights) g[f[0]].push_back({f[1], f[2]});
    const int INF = 1e9;
    vector<int> dist(n, INF); dist[src]=0;
    queue<pair<int,int>> q; q.push({src,0});
    int stops=0;
    while(!q.size() && stops<=K){
        int sz=q.size();
        while(sz--){ auto [u,d]=q.front(); q.pop();
            for(auto &e: g[u]) if(d+e.second < dist[e.first]){ dist[e.first]=d+e.second; q.push({e.first, dist[e.first]}); }
        }
        ++stops;
    }
    return dist[dst]==INF? -1 : dist[dst];
}
