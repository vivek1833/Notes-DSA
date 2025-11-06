
#include <vector>
using namespace std;
int numRollsToTarget(int d, int f, int target){
    vector<vector<int>> memo(d+1, vector<int>(target+1, -1));
    function<int(int,int)> dfs = [&](int rolls, int rem){
        if(rem<0) return 0; if(rolls==0) return rem==0?1:0;
        if(memo[rolls][rem]!=-1) return memo[rolls][rem];
        long long ans=0, MOD=1e9+7;
        for(int face=1;face<=f;++face) ans=(ans + dfs(rolls-1, rem-face))%MOD;
        return memo[rolls][rem]=ans;
    };
    return dfs(d, target);
}
