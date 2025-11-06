
#include <vector>
#include <algorithm>
using namespace std;
int scheduleCourse(vector<vector<int>>& courses){
    sort(courses.begin(), courses.end(), [](auto &a, auto &b){ return a[1]<b[1]; });
    int time=0;
    priority_queue<int> pq;
    for(auto &c: courses){
        time+=c[0]; pq.push(c[0]);
        if(time>c[1]){ time -= pq.top(); pq.pop(); }
    }
    return pq.size();
}
