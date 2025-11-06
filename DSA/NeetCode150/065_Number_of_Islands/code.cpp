
#include <vector>
using namespace std;
void dfsNI(vector<vector<char>>& g, int i, int j){
    if(i<0||j<0||i>= (int)g.size()||j>= (int)g[0].size()||g[i][j]!='1') return;
    g[i][j]='0';
    dfsNI(g,i+1,j); dfsNI(g,i-1,j); dfsNI(g,i,j+1); dfsNI(g,i,j-1);
}
int numIslands(vector<vector<char>>& grid){
    int m=grid.size(), n=grid[0].size(), c=0;
    for(int i=0;i<m;++i) for(int j=0;j<n;++j) if(grid[i][j]=='1'){ ++c; dfsNI(grid,i,j); }
    return c;
}
