#include<bits/stdc++.h>
using namespace std;

// Problem: https://practice.geeksforgeeks.org/problems/perfect-sum-problem5633/1#
class Solution{
private:
    #define mod 1000000007
    int solve(int i, int n, int sum, int arr[])
    {
        if(sum==0)      return 1;
        if(i>=n || sum<0)    return 0;
        
        int take=solve(i+1,n,sum-arr[i],arr);
        int ntake=solve(i+1,n,sum,arr);
        
        return ((take%mod)+(ntake%mod))%mod;
    }
    
    int solveMem(int i, int n, int sum, int arr[], vector<vector<int>> &dp)
    {
        if(sum==0)      return 1;
        if(i>=n || sum<0)    return 0;
        if(dp[i][sum]!=-1)  return dp[i][sum];
        
        int take=solveMem(i+1,n,sum-arr[i],arr,dp);
        int ntake=solveMem(i+1,n,sum,arr,dp);
        
        return dp[i][sum]=((take%mod)+(ntake%mod))%mod;
    }

public:
	int perfectSum(int arr[], int n, int sum)
	{
	    sort(arr,arr+n);
	    vector<vector<int>> dp(n+1,vector<int>(sum+1,-1));
	    return solveMem(0,n,sum,arr,dp);
	}
};