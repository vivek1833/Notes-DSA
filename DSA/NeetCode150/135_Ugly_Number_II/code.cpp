
#include <vector>
using namespace std;
int nthUglyNumber(int n){
    vector<int> res(1,1);
    int i2=0,i3=0,i5=0;
    while(res.size()<n){
        int v=min({res[i2]*2, res[i3]*3, res[i5]*5});
        res.push_back(v);
        if(v==res[i2]*2) ++i2;
        if(v==res[i3]*3) ++i3;
        if(v==res[i5]*5) ++i5;
    }
    return res.back();
}
