
#include <string>
#include <vector>
#include <unordered_set>
using namespace std;
bool wordBreak(string s, vector<string>& wordDict){
    unordered_set<string> dict(wordDict.begin(), wordDict.end());
    vector<int> memo(s.size(), -1);
    function<bool(int)> dfs = [&](int i){
        if(i==(int)s.size()) return true;
        if(memo[i]!=-1) return memo[i];
        for(int j=i+1;j<= (int)s.size(); ++j){
            if(dict.count(s.substr(i,j-i)) && dfs(j)) return memo[i]=1;
        }
        return memo[i]=0;
    };
    return dfs(0);
}
