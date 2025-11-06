#include <vector>
#include <unordered_map>
using namespace std;

vector<int> twoSum(vector<int>& nums, int target) {
    unordered_map<int,int> mp; // value -> index
    for (int i = 0; i < (int)nums.size(); ++i) {
        int need = target - nums[i];
        if (mp.find(need) != mp.end()) return {mp[need], i};
        mp[nums[i]] = i;
    }
    return {};
}
