#include <vector>
#include <unordered_set>
using namespace std;

bool containsDuplicate(vector<int>& nums) {
    unordered_set<int> seen;
    for (int x: nums) {
        if (seen.find(x) != seen.end()) return true;
        seen.insert(x);
    }
    return false;
}
