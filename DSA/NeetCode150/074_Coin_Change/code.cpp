
#include <vector>
#include <algorithm>
using namespace std;
int coinChange(vector<int>& coins, int amount){
    const int INF = 1e9;
    vector<int> memo(amount+1, -2);
    function<int(int)> dfs = [&](int rem){
        if(rem==0) return 0;
        if(rem<0) return INF;
        if(memo[rem]!=-2) return memo[rem];
        int ans=INF;
        for(int c: coins) ans=min(ans, 1+dfs(rem-c));
        return memo[rem]=ans;
    };
    int r=dfs(amount);
    return r>=INF?-1:r;
}
