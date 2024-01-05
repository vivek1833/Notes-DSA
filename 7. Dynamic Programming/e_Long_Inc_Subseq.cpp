#include <bits/stdc++.h>
using namespace std;

class Solution {
private:
    int solve(int curr, int prev, int n, vector<int> &nums)
    {
        if(curr>=n) return 0;

        int inc=0;
        if(prev==-1 || nums[curr]>nums[prev])
           inc=1+solve(curr+1,curr,n,nums);
        int excl=solve(curr+1,prev,n,nums);

        return max(inc,excl);
    }

    int solveMem(int curr, int prev, int n, vector<int> &nums, vector<vector<int>> &dp)
    {
        if(curr>=n) return 0;
        if(dp[curr][prev+1]!=-1) return dp[curr][prev+1];

        int inc=0;
        if(prev==-1 || nums[curr]>nums[prev])
           inc=1+solveMem(curr+1,curr,n,nums,dp);
        int excl=solveMem(curr+1,prev,n,nums,dp);

        return dp[curr][prev+1]=max(inc,excl);
    }

public:
    int lengthOfLIS(vector<int> &nums) 
    {
        int n=nums.size();
        vector<vector<int>> dp(n+1,vector<int>(n+1,-1));
        // return solve(0,-1,n,nums);
        return solveMem(0,-1,n,nums,dp);
    }
};