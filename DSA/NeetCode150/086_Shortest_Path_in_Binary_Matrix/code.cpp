
#include <vector>
#include <queue>
using namespace std;
int shortestPathBinaryMatrix(vector<vector<int>>& grid){
    int m=grid.size(), n=grid[0].size();
    if(grid[0][0]!=0 || grid[m-1][n-1]!=0) return -1;
    queue<pair<int,int>> q; q.push({0,0}); grid[0][0]=1;
    int dirs[8][2]={{1,0},{-1,0},{0,1},{0,-1},{1,1},{1,-1},{-1,1},{-1,-1}};
    int steps=0;
    while(!q.empty()){
        int sz=q.size(); ++steps;
        while(sz--){
            auto [x,y]=q.front(); q.pop();
            if(x==m-1 && y==n-1) return steps;
            for(auto &d: dirs){
                int nx=x+d[0], ny=y+d[1];
                if(nx>=0&&ny>=0&&nx<m&&ny<n&&grid[nx][ny]==0){ grid[nx][ny]=1; q.push({nx,ny}); }
            }
        }
    }
    return -1;
}
