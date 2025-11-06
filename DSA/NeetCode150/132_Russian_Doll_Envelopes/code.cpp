
#include <vector>
#include <algorithm>
using namespace std;
int maxEnvelopes(vector<vector<int>>& env){
    sort(env.begin(), env.end(), [](auto &a, auto &b){ if(a[0]==b[0]) return a[1]>b[1]; return a[0]<b[0]; });
    vector<int> dp;
    for(auto &e: env){
        auto it = lower_bound(dp.begin(), dp.end(), e[1]);
        if(it==dp.end()) dp.push_back(e[1]); else *it=e[1];
    }
    return dp.size();
}
