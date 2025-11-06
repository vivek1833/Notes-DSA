
#include <vector>
#include <queue>
using namespace std;
int findKthLargest(vector<int>& nums, int k){
    priority_queue<int, vector<int>, greater<int>> pq;
    for(int x: nums){ pq.push(x); if((int)pq.size()>k) pq.pop(); }
    return pq.top();
}
