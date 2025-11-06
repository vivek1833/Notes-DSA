
#include <vector>
using namespace std;
long long hrs(vector<int>& piles, long long k){
    long long h=0;
    for(int p: piles) h += (p + k - 1)/k;
    return h;
}
int minEatingSpeed(vector<int>& piles, int h){
    long long l=1, r=*max_element(piles.begin(), piles.end()), ans=r;
    while(l<=r){
        long long m=(l+r)/2;
        if(hrs(piles,m)<=h){ ans=m; r=m-1; } else l=m+1;
    }
    return (int)ans;
}
