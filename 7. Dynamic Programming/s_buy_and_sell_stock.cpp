#include<bits/stdc++.h>
using namespace std;
 
class Solution {
public:
    int maxProfit(vector<int> &prices) 
    {
        int current=prices[0], ans=0;

        for(int i=1; i<prices.size(); i++) {
            if(prices[i]>current)    ans+=prices[i]-current;
            current=prices[i];
        }

        return ans;
    }
};