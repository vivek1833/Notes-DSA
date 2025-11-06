
#include <vector>
using namespace std;
int jump(vector<int>& nums){
    int n=nums.size(), jumps=0, curEnd=0, far=0;
    for(int i=0;i<n-1;++i){
        far = max(far, i+nums[i]);
        if(i==curEnd){ ++jumps; curEnd=far; }
    }
    return jumps;
}
