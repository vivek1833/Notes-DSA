
#include <vector>
using namespace std;
int lengthOfLIS(vector<int>& nums){
    int n=nums.size();
    vector<int> memo(n,-1);
    function<int(int)> dfs = [&](int idx){
        if(memo[idx]!=-1) return memo[idx];
        int best=1;
        for(int j=idx+1;j<n;++j) if(nums[j]>nums[idx]) best=max(best, 1+dfs(j));
        return memo[idx]=best;
    };
    int ans=0;
    for(int i=0;i<n;++i) ans=max(ans, dfs(i));
    return ans;
}
