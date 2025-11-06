
#include <vector>
using namespace std;
vector<vector<int>> resP;
void backtrackP(vector<int>& a, int i){
    if(i==(int)a.size()){ resP.push_back(a); return; }
    for(int j=i;j<(int)a.size();++j){ swap(a[i],a[j]); backtrackP(a,i+1); swap(a[i],a[j]); }
}
vector<vector<int>> permute(vector<int>& nums){ resP.clear(); backtrackP(nums,0); return resP; }
