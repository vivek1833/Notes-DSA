
#include <vector>
#include <queue>
using namespace std;
int orangesRotting(vector<vector<int>>& g){
    int m=g.size(), n=g[0].size();
    queue<pair<int,int>> q; int fresh=0, t=0;
    for(int i=0;i<m;++i) for(int j=0;j<n;++j){
        if(g[i][j]==2) q.push({i,j});
        else if(g[i][j]==1) fresh++;
    }
    int dirs[4][2]={{1,0},{-1,0},{0,1},{0,-1}};
    while(!q.empty() && fresh){
        int sz=q.size(); ++t;
        while(sz--){
            auto [x,y]=q.front(); q.pop();
            for(auto &d: dirs){
                int nx=x+d[0], ny=y+d[1];
                if(nx>=0&&ny>=0&&nx<m&&ny<n&&g[nx][ny]==1){
                    g[nx][ny]=2; --fresh; q.push({nx,ny});
                }
            }
        }
    }
    return fresh? -1 : t;
}
