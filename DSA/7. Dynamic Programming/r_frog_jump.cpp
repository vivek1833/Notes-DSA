#include<bits/stdc++.h>
using namespace std;
 
// Problem: https://leetcode.com/problems/frog-jump/description/
class Solution {
private:
    bool solve(int i, int k, int n, vector<int> &nums)
    {
        if(i==n-1)  return true;
        if(i>=n)    return false;

        bool c1=false, c2=false, c3=false;
        for(int j=i+1; j<n; j++) {
            if(nums[j]==nums[i]+k-1)    c1 = solve(j,k-1,n,nums);
            if(nums[j]==nums[i]+k)      c2 = solve(j,k,n,nums);
            if(nums[j]==nums[i]+k+1)    c3 = solve(j,k+1,n,nums);
        }

        return c1 || c2 || c3;    
    }

    bool solveMem(int i, int k, int n, vector<vector<int>> &dp, vector<int> &nums)
    {
        if(i==n-1)  return true;
        if(i>=n)    return false;
        if(dp[i][k]!=-1)    return dp[i][k];

        bool c1=false, c2=false, c3=false;
        for(int j=i+1; j<n; j++) {
            if(nums[j]==nums[i]+k-1)    c1 = solveMem(j,k-1,n,dp,nums);
            if(nums[j]==nums[i]+k)      c2 = solveMem(j,k,n,dp,nums);
            if(nums[j]==nums[i]+k+1)    c3 = solveMem(j,k+1,n,dp,nums);
        }

        return dp[i][k] = (c1 || c2 || c3);    
    }

public:
    bool canCross(vector<int> &nums) 
    {
        int n = nums.size();
        if(n==1)    return true;
        if(nums[1]!=1)  return false;
        vector<vector<int>> dp(n+1, vector<int>(2000+1,-1));
        return solveMem(1,1,n,dp,nums);
    }
};