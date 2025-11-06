
#include <vector>
#include <map>
using namespace std;
int maxChunksToSortedII(vector<int>& arr){
    map<int,int> c1, c2; int cnt=0;
    for(int i=0;i<arr.size();++i){
        c1[arr[i]]++;
        c2[arr[i]]--;
        if(c1==c2) ++cnt;
    }
    return cnt;
}
