#include <bits/stdc++.h>
using namespace std;

class Solution {
private:
    int solve(int i, int j, int n, int m, string s1, string s2)
    {
        if(i>=n || j>=m)    return 0;
        if(s1[i]==s2[j])    return 1+solve(i+1,j+1,n,m,s1,s2);
        
        int one=solve(i+1,j,n,m,s1,s2);
        int two=solve(i,j+1,n,m,s1,s2);

        return max(one,two);
    }

    int solveMemo(int i, int j, int n, int m, string s1, string s2, vector<vector<int>> &dp)
    {
        if(i>=n || j>=m)    return 0;
        if(dp[i][j]!=-1)    return dp[i][j];
        if(s1[i]==s2[j])    return dp[i][j]=1+solveMemo(i+1,j+1,n,m,s1,s2,dp);
        
        int one=solveMemo(i+1,j,n,m,s1,s2,dp);
        int two=solveMemo(i,j+1,n,m,s1,s2,dp);

        return dp[i][j]=max(one,two);
    }

public:
    int LCS(string s1, string s2, int n, int m)
    {
        // return solve(0, 0, n, m, s1, s2);
        vector<vector<int>> dp(n+1, vector<int>(m+1, -1));
        return solveMemo(0,0,n,m,s1,s2,dp);
    }
};
