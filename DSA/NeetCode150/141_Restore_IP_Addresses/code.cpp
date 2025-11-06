
#include <string>
#include <vector>
using namespace std;
vector<string> resIP; string curIP;
void dfsIP(string &s, int idx, int seg){
    if(seg==4){ if(idx==s.size()) resIP.push_back(curIP); return; }
    for(int len=1; len<=3 && idx+len<=s.size(); ++len){
        if(len>1 && s[idx]=='0') break;
        int val = stoi(s.substr(idx,len));
        if(val>255) break;
        int oldlen = curIP.size();
        if(!curIP.empty()) curIP.push_back('.');
        curIP += s.substr(idx,len);
        dfsIP(s, idx+len, seg+1);
        curIP.resize(oldlen);
    }
}
vector<string> restoreIpAddresses(string s){ resIP.clear(); curIP=""; dfsIP(s,0,0); return resIP; }
