
#include <vector>
using namespace std;
int maxProfit(vector<int>& prices){
    int ans=0;
    for(int i=1;i<(int)prices.size();++i) if(prices[i]>prices[i-1]) ans+=prices[i]-prices[i-1];
    return ans;
}
