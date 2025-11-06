
#include <vector>
#include <queue>
using namespace std;
vector<vector<int>> kClosest(vector<vector<int>>& points, int k){
    priority_queue<pair<int,int>> pq;
    for(int i=0;i<(int)points.size();++i){
        int d = points[i][0]*points[i][0] + points[i][1]*points[i][1];
        pq.push({d,i}); if((int)pq.size()>k) pq.pop();
    }
    vector<vector<int>> res;
    while(!pq.empty()){ res.push_back(points[pq.top().second]); pq.pop(); }
    return res;
}
