
#include <vector>
#include <string>
#include <unordered_map>
using namespace std;
// Simplified union-find by email mapping
vector<vector<string>> accountsMerge(vector<vector<string>>& accounts){
    unordered_map<string, string> owner;
    unordered_map<string, string> parent;
    function<string(string)> findp = [&](string x){ return parent.count(x)? (parent[x]==x? x: parent[x]=findp(parent[x])) : (parent[x]=x); };
    for(auto &acc: accounts){
        string name=acc[0];
        for(int i=1;i<acc.size();++i){ owner[acc[i]]=name; if(!parent.count(acc[i])) parent[acc[i]]=acc[i]; parent[findp(acc[i])] = findp(acc[1]); }
    }
    unordered_map<string, vector<string>> groups;
    for(auto &p: owner) groups[findp(p.first)].push_back(p.first);
    vector<vector<string>> res;
    for(auto &g: groups){ auto v=g.second; sort(v.begin(), v.end()); v.insert(v.begin(), owner[g.first]); res.push_back(v); }
    return res;
}
