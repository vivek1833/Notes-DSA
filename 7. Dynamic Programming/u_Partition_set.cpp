#include<bits/stdc++.h>
using namespace std;
 
// Problem: https://practice.geeksforgeeks.org/problems/subset-sum-problem2014/1 
class Solution{
private:
    int solve(int i, int sum, int n, int arr[])
    {
        if(i>=n)    return 0;
        if(sum==0)  return 1;
        
        int take=0;
        if(sum-arr[i]>=0)   take=solve(i+1,sum-arr[i],n,arr);
        int ntake=solve(i+1,sum,n,arr);
        
        return (take|ntake);
    }

    int solveMem(int i, int sum, int n, int arr[], vector<vector<int>> &dp)
    {
        if(i>=n)    return 0;
        if(sum==0)  return 1;
        if(dp[i][sum]!=-1) return dp[i][sum];
        
        int take=0;
        if(sum-arr[i]>=0)   take=solve(i+1,sum-arr[i],n,arr,dp);
        int ntake=solve(i+1,sum,n,arr,dp);
        
        return dp[i][sum]=(take|ntake);
    }
    
public:
    int equalPartition(int n, int arr[])
    {
        int sum=0;
        for(int i=0; i<n; i++) {
            sum+=arr[i];
        }
        if(sum%2!=0)    return 0;
        
        vector<vector<int>> dp(n+1,vector<int>(sum+1,-1));
        return solveMem(0,sum/2,n,arr,dp);
    }
};