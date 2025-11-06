
#include <vector>
using namespace std;
int maxProfitCooldown(vector<int>& prices){
    int n=prices.size();
    if(n==0) return 0;
    int sold=0, hold=-prices[0], rest=0;
    for(int i=1;i<n;++i){
        int prev_sold = sold;
        sold = hold + prices[i];
        hold = max(hold, rest - prices[i]);
        rest = max(rest, prev_sold);
    }
    return max(sold, rest);
}
