
#include <vector>
using namespace std;
vector<vector<int>> resCS;
void dfsCS(vector<int>& c, int i, int t, vector<int>& cur){
    if(t==0){ resCS.push_back(cur); return; }
    if(i==(int)c.size() || t<0) return;
    cur.push_back(c[i]); dfsCS(c,i,t-c[i],cur); cur.pop_back();
    dfsCS(c,i+1,t,cur);
}
vector<vector<int>> combinationSum(vector<int>& candidates, int target){ resCS.clear(); vector<int> cur; dfsCS(candidates,0,target,cur); return resCS; }
