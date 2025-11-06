
#include <vector>
using namespace std;
int dfsMA(vector<vector<int>>& g, int i, int j){
    if(i<0||j<0||i>= (int)g.size()||j>= (int)g[0].size()||g[i][j]==0) return 0;
    g[i][j]=0;
    return 1 + dfsMA(g,i+1,j)+dfsMA(g,i-1,j)+dfsMA(g,i,j+1)+dfsMA(g,i,j-1);
}
int maxAreaOfIsland(vector<vector<int>>& grid){
    int m=grid.size(), n=grid[0].size(), ans=0;
    for(int i=0;i<m;++i) for(int j=0;j<n;++j) ans=max(ans, dfsMA(grid,i,j));
    return ans;
}
