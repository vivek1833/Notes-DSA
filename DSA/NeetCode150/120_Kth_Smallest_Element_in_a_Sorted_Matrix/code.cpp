
#include <vector>
#include <queue>
using namespace std;
int kthSmallest(vector<vector<int>>& mat, int k){
    int n=mat.size(), m=mat[0].size();
    priority_queue<pair<int,int>, vector<pair<int,int>>, greater<pair<int,int>>> pq;
    for(int j=0;j<m;++j) pq.push({mat[0][j], j});
    vector<int> idx(m,0);
    int val=0;
    while(k--){
        auto [v,col]=pq.top(); pq.pop();
        val=v;
        int r = ++idx[col];
        if(r<n) pq.push({mat[r][col], col});
    }
    return val;
}
