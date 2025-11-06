
#include <vector>
using namespace std;
int dirs4[4][2]={{1,0},{-1,0},{0,1},{0,-1}};
int dfsLIP(vector<vector<int>>& m, int i, int j, vector<vector<int>>& memo){
    if(memo[i][j]) return memo[i][j];
    int n=m.size(), p=m[0].size(), best=1;
    for(auto &d: dirs4){
        int ni=i+d[0], nj=j+d[1];
        if(ni>=0&&nj>=0&&ni<n&&nj<p && m[ni][nj]>m[i][j]) best = max(best, 1 + dfsLIP(m, ni, nj, memo));
    }
    return memo[i][j]=best;
}
int longestIncreasingPath(vector<vector<int>>& matrix){
    if(matrix.empty()) return 0;
    int n=matrix.size(), m=matrix[0].size(), ans=0;
    vector<vector<int>> memo(n, vector<int>(m,0));
    for(int i=0;i<n;++i) for(int j=0;j<m;++j) ans=max(ans, dfsLIP(matrix,i,j,memo));
    return ans;
}
