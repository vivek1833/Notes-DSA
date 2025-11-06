
#include <vector>
using namespace std;
vector<vector<int>> resAP; vector<int> curAP;
void dfsAP(vector<vector<int>>& g, int u){
    curAP.push_back(u);
    if(u==(int)g.size()-1) resAP.push_back(curAP);
    else for(int v: g[u]) dfsAP(g,v);
    curAP.pop_back();
}
vector<vector<int>> allPathsSourceTarget(vector<vector<int>>& graph){ resAP.clear(); curAP.clear(); dfsAP(graph,0); return resAP; }
