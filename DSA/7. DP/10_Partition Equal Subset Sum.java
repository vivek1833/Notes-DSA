import java.util.*;

/* 
    Problem Link: https://leetcode.com/problems/partition-equal-subset-sum/description/

    Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements in both subsets is equal or false otherwise.

    ----------------------------

    Example 1:
        Input: nums = [1,5,11,5]
        Output: true
        Explanation: The array can be partitioned as [1, 5, 5] and [11].
    
    Example 2:
        Input: nums = [1,2,3,5]
        Output: false
        Explanation: The array cannot be partitioned into equal sum subsets.

    ----------------------------

    Constraints:
        - 1 <= nums.length <= 200
        - 1 <= nums[i] <= 100
*/

class Solution {
    private boolean solve(int i, int n, int sum, int[] nums, Boolean[][] dp) {
        if(sum==0)          return true;
        if(sum<0 || i>=n)   return false;
        if(dp[i][sum]!=null)      return dp[i][sum];

        boolean take = solve(i+1,n,sum-nums[i],nums,dp);
        boolean notTake = solve(i+1,n,sum,nums,dp);
        
        return dp[i][sum] = take || notTake;
    }

    public boolean canPartition(int[] nums) 
    {
        int n=nums.length;
        int sum= Arrays.stream(nums).sum();
        Boolean[][] dp = new Boolean[n+1][sum+1];

        if(sum%2!=0)    return false;

        return solve(0,n,sum/2,nums,dp);
    }
}