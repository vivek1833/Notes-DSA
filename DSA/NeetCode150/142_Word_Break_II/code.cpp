
#include <string>
#include <vector>
#include <unordered_set>
using namespace std;
unordered_map<string, vector<string>> memoWB2;
vector<string> dfsWB2(string s, unordered_set<string>& dict){
    if(memoWB2.count(s)) return memoWB2[s];
    vector<string> res;
    if(dict.count(s)) res.push_back(s);
    for(int i=1;i<s.size();++i){
        string pref = s.substr(0,i);
        if(dict.count(pref)){
            auto suf = dfsWB2(s.substr(i), dict);
            for(auto &x: suf) res.push_back(pref + " " + x);
        }
    }
    return memoWB2[s]=res;
}
vector<string> wordBreak2(string s, vector<string>& wordDict){ memoWB2.clear(); unordered_set<string> dict(wordDict.begin(), wordDict.end()); return dfsWB2(s, dict); }
