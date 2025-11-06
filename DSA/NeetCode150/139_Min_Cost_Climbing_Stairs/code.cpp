
#include <vector>
using namespace std;
int minCostClimbingStairs(vector<int>& cost){
    int n=cost.size();
    vector<int> memo(n+1, -1);
    function<int(int)> dfs = [&](int i){
        if(i>=n) return 0;
        if(memo[i]!=-1) return memo[i];
        return memo[i]=cost[i]+min(dfs(i+1), dfs(i+2));
    };
    return min(dfs(0), dfs(1));
}
