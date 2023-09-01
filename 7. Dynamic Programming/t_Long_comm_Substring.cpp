#include<bits/stdc++.h>
using namespace std;
 
class Solution{
private:
    int solve(int i, int j, int n, int m, string s1, string s2, int cnt)
    {
        if(i>=n || j>=m)    return cnt;

        if(s1[i]==s2[j])    cnt=solve(i+1,j+1,n,m,s1,s2,cnt+1);
        else {
            int a=solve(i+1,j,n,m,s1,s2,0);
            int b=solve(i,j+1,n,m,s1,s2,0);
            cnt=max(cnt,max(a,b));
        }                

        return cnt;
    }
    
public:
    int longestCommonSubstr(string s1, string s2, int n, int m)
    {
        // int ans=solve(0,0,n,m,s1,s2,0);
        // return ans;

        vector<vector<int>> dp(n+1,vector<int>(m+1,0));
        int ans=0;

        for(int i=1;i<=n;i++) {
            for(int j=1;j<=m;j++) {
                if(s1[i-1]==s2[j-1])    dp[i][j]=1+dp[i-1][j-1];
                else    dp[i][j]=0;

                ans=max(ans,dp[i][j]);
            }
        }

        return ans;
    }
};