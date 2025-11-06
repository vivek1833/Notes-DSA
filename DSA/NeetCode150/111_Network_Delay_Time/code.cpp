
#include <vector>
#include <queue>
using namespace std;
int networkDelayTime(vector<vector<int>>& times, int N, int K){
    vector<vector<pair<int,int>>> g(N+1);
    for(auto &t: times) g[t[0]].push_back({t[1], t[2]});
    const int INF = 1e9;
    vector<int> dist(N+1, INF); dist[K]=0;
    priority_queue<pair<int,int>, vector<pair<int,int>>, greater<pair<int,int>>> pq;
    pq.push({0,K});
    while(!pq.empty()){
        auto [d,u]=pq.top(); pq.pop();
        if(d>dist[u]) continue;
        for(auto &e: g[u]) if(d+e.second < dist[e.first]){ dist[e.first]=d+e.second; pq.push({dist[e.first], e.first}); }
    }
    int ans=0;
    for(int i=1;i<=N;++i){ if(dist[i]==INF) return -1; ans=max(ans, dist[i]); }
    return ans;
}
