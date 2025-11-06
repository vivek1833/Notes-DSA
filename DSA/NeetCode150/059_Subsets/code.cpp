
#include <vector>
using namespace std;
vector<vector<int>> resSub;
void dfsSub(vector<int>& nums, int i, vector<int>& cur){
    if(i==(int)nums.size()){ resSub.push_back(cur); return; }
    dfsSub(nums,i+1,cur);
    cur.push_back(nums[i]); dfsSub(nums,i+1,cur); cur.pop_back();
}
vector<vector<int>> subsets(vector<int>& nums){ resSub.clear(); vector<int> cur; dfsSub(nums,0,cur); return resSub; }
