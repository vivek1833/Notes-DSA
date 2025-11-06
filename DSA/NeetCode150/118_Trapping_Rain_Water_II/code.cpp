
#include <vector>
#include <queue>
using namespace std;
int trapRainWater(vector<vector<int>>& h){
    int m=h.size(), n=h[0].size();
    priority_queue<tuple<int,int,int>, vector<tuple<int,int,int>>, greater<tuple<int,int,int>>> pq;
    vector<vector<int>> vis(m, vector<int>(n,0));
    for(int i=0;i<m;++i) for(int j=0;j<n;++j) if(i==0||j==0||i==m-1||j==n-1){ pq.push({h[i][j],i,j}); vis[i][j]=1; }
    int res=0, dirs[4][2]={{1,0},{-1,0},{0,1},{0,-1}};
    int mx=0;
    while(!pq.empty()){
        auto [ht,i,j]=pq.top(); pq.pop();
        mx = max(mx, ht);
        for(auto &d: dirs){
            int ni=i+d[0], nj=j+d[1];
            if(ni>=0&&nj>=0&&ni<m&&nj<n && !vis[ni][nj]){
                vis[ni][nj]=1;
                if(h[ni][nj]<mx) res += mx - h[ni][nj];
                pq.push({h[ni][nj], ni, nj});
            }
        }
    }
    return res;
}
