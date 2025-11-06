
#include <vector>
#include <string>
using namespace std;
bool isPal(const string& s, int l, int r){ while(l<r) if(s[l++]!=s[r--]) return false; return true; }
vector<vector<string>> resPP; vector<string> curPP;
void dfsPP(const string& s, int i){
    if(i==(int)s.size()){ resPP.push_back(curPP); return; }
    for(int j=i;j<(int)s.size();++j) if(isPal(s,i,j)){ curPP.push_back(s.substr(i,j-i+1)); dfsPP(s,j+1); curPP.pop_back(); }
}
vector<vector<string>> partition(string s){ resPP.clear(); curPP.clear(); dfsPP(s,0); return resPP; }
