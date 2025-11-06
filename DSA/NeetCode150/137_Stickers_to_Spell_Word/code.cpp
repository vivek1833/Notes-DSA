
#include <vector>
#include <string>
using namespace std;
int minStickers(vector<string>& stickers, string target){
    int m=stickers.size();
    vector<vector<int>> cnt(m, vector<int>(26,0));
    for(int i=0;i<m;++i) for(char c: stickers[i]) cnt[i][c-'a']++;
    unordered_map<string,int> memo;
    memo[""] = 0;
    function<int(string)> dfs = [&](string tar){
        if(memo.count(tar)) return memo[tar];
        vector<int> tarcnt(26,0);
        for(char c: tar) tarcnt[c-'a']++;
        int ans=1e9;
        for(int i=0;i<m;++i){
            if(cnt[i][tar[0]-'a']==0) continue;
            string s;
            for(int k=0;k<26;++k){
                if(tarcnt[k]>0){
                    int rem = max(0, tarcnt[k]-cnt[i][k]);
                    for(int r=0;r<rem;++r) s.push_back('a'+k);
                }
            }
            int tmp = dfs(s);
            if(tmp!=-1) ans = min(ans, 1+tmp);
        }
        memo[tar] = (ans==1e9)? -1 : ans;
        return memo[tar];
    };
    return dfs(target);
}
