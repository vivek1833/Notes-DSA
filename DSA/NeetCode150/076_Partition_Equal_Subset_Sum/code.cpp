
#include <vector>
using namespace std;
bool canPartition(vector<int>& nums){
    int sum=0; for(int x: nums) sum+=x;
    if(sum%2) return false;
    int target=sum/2;
    vector<int> memo(target+1, -1);
    function<int(int,int)> dfs = [&](int i, int rem){
        if(rem==0) return 1;
        if(i==(int)nums.size() || rem<0) return 0;
        if(memo[rem]!=-1) return memo[rem];
        if(dfs(i+1, rem-nums[i])) return memo[rem]=1;
        return memo[rem]=dfs(i+1, rem);
    };
    return dfs(0, target);
}
