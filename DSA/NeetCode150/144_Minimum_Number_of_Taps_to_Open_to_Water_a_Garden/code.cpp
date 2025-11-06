
#include <vector>
using namespace std;
int minTaps(int n, vector<int>& ranges){
    vector<int> maxR(n+1,0);
    for(int i=0;i<=n;++i){
        int l = max(0, i-ranges[i]);
        int r = min(n, i+ranges[i]);
        maxR[l] = max(maxR[l], r);
    }
    int taps=0, curEnd=0, far=0, i=0;
    while(curEnd<n){
        while(i<=curEnd){ far = max(far, maxR[i]); ++i; }
        if(far==curEnd) return -1;
        ++taps; curEnd=far;
    }
    return taps;
}
