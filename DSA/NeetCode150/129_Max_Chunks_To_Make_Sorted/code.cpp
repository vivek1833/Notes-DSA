
#include <vector>
using namespace std;
int maxChunksToSorted(vector<int>& arr){
    int cnt=0, mx=0;
    for(int i=0;i<arr.size();++i){ mx=max(mx, arr[i]); if(mx==i) ++cnt; }
    return cnt;
}
