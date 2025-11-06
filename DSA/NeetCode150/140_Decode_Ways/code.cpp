
#include <string>
#include <vector>
using namespace std;
int numDecodings(string s){
    int n=s.size();
    vector<int> memo(n+1, -1);
    function<int(int)> dfs = [&](int i){
        if(i==n) return 1;
        if(s[i]=='0') return 0;
        if(memo[i]!=-1) return memo[i];
        int ans = dfs(i+1);
        if(i+1<n && (s[i]=='1' || (s[i]=='2' && s[i+1]<='6'))) ans += dfs(i+2);
        return memo[i]=ans;
    };
    return dfs(0);
}
