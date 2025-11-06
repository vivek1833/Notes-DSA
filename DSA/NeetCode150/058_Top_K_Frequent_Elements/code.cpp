
#include <vector>
#include <unordered_map>
#include <queue>
using namespace std;
vector<int> topKFrequent(vector<int>& nums, int k){
    unordered_map<int,int> c; for(int x: nums) c[x]++;
    priority_queue<pair<int,int>> pq;
    for(auto &p: c) pq.push({p.second, p.first});
    vector<int> res; while(k-- && !pq.empty()){ res.push_back(pq.top().second); pq.pop(); }
    return res;
}
