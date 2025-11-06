
#include <vector>
using namespace std;
void dfsPA(vector<vector<int>>& h, int i, int j, vector<vector<int>>& vis){
    int m=h.size(), n=h[0].size();
    if(vis[i][j]) return; vis[i][j]=1;
    if(i>0 && h[i-1][j]>=h[i][j]) dfsPA(h,i-1,j,vis);
    if(j>0 && h[i][j-1]>=h[i][j]) dfsPA(h,i,j-1,vis);
    if(i+1<m && h[i+1][j]>=h[i][j]) dfsPA(h,i+1,j,vis);
    if(j+1<n && h[i][j+1]>=h[i][j]) dfsPA(h,i,j+1,vis);
}
vector<vector<int>> pacificAtlantic(vector<vector<int>>& heights){
    int m=heights.size(), n=heights[0].size();
    vector<vector<int>> pac(m, vector<int>(n,0)), atl(m, vector<int>(n,0));
    for(int i=0;i<m;++i){ dfsPA(heights,i,0,pac); dfsPA(heights,i,n-1,atl); }
    for(int j=0;j<n;++j){ dfsPA(heights,0,j,pac); dfsPA(heights,m-1,j,atl); }
    vector<vector<int>> res;
    for(int i=0;i<m;++i) for(int j=0;j<n;++j) if(pac[i][j]&&atl[i][j]) res.push_back({i,j});
    return res;
}
