#include <vector>
using namespace std;

int maxProfit(vector<int>& prices) {
    int minP = INT_MAX, ans = 0;
    for (int p: prices) {
        minP = min(minP, p);
        ans = max(ans, p - minP);
    }
    return ans;
}
