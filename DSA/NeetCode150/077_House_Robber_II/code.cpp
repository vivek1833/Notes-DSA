
#include <vector>
using namespace std;
int robRange(vector<int>& nums, int l, int r){
    int incl=0, excl=0;
    for(int i=l;i<=r;++i){
        int nex = max(incl, excl+nums[i]);
        excl = incl; incl = nex;
    }
    return incl;
}
int rob(vector<int>& nums){
    int n=nums.size();
    if(n==1) return nums[0];
    return max(robRange(nums,0,n-2), robRange(nums,1,n-1));
}
