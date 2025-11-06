
#include <vector>
using namespace std;
bool searchMatrix(vector<vector<int>>& m, int target){
    int n=m.size(), k=m[0].size();
    int l=0, r=n*k-1;
    while(l<=r){
        int mid=l+(r-l)/2;
        int val=m[mid/k][mid%k];
        if(val==target) return true;
        if(val<target) l=mid+1; else r=mid-1;
    }
    return false;
}
