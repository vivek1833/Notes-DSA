
#include <vector>
#include <unordered_map>
#include <string>
using namespace std;
double dfsED(const string& u, const string& v, unordered_map<string, vector<pair<string,double>>>& g, unordered_map<string,int>& vis){
    if(!g.count(u) || !g.count(v)) return -1.0;
    if(u==v) return 1.0;
    vis[u]=1;
    for(auto &e: g[u]) if(!vis[e.first]){
        double d = dfsED(e.first, v, g, vis);
        if(d>0) return e.second * d;
    }
    return -1.0;
}
vector<double> calcEquation(vector<vector<string>>& eq, vector<double>& val, vector<vector<string>>& queries){
    unordered_map<string, vector<pair<string,double>>> g;
    for(int i=0;i<eq.size();++i){ g[eq[i][0]].push_back({eq[i][1], val[i]}); g[eq[i][1]].push_back({eq[i][0], 1.0/val[i]}); }
    vector<double> res;
    for(auto &q: queries){
        unordered_map<string,int> vis;
        double r = dfsED(q[0], q[1], g, vis);
        res.push_back(r);
    }
    return res;
}
