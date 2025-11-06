
#include <vector>
using namespace std;
vector<int> sortedSquares(vector<int>& A){
    int n=A.size(); vector<int> res(n);
    int l=0, r=n-1, idx=n-1;
    while(l<=r){
        int lv=A[l]*A[l], rv=A[r]*A[r];
        if(lv>rv){ res[idx--]=lv; ++l; } else { res[idx--]=rv; --r; }
    }
    return res;
}
