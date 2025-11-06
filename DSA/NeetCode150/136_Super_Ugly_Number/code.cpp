
#include <vector>
using namespace std;
int nthSuperUglyNumber(int n, vector<int>& primes){
    vector<int> res(1,1), idx(primes.size(),0);
    while(res.size()<n){
        int next=INT_MAX;
        for(int i=0;i<primes.size();++i) next=min(next, primes[i]*res[idx[i]]);
        res.push_back(next);
        for(int i=0;i<primes.size();++i) if(primes[i]*res[idx[i]]==next) ++idx[i];
    }
    return res.back();
}
