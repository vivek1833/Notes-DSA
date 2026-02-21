/*
    Problem: https://leetcode.com/problems/coin-change-ii/

    You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

    Return the number of combinations that make up that amount. If that amount of money cannot be made up by any combination of the coins, return 0.

    You may assume that you have an infinite number of each kind of coin.

    ----------------------------

    Example 1:
        Input: amount = 5, coins = [1,2,5]
        Output: 4
        Explanation: there are four ways to make up the amount:
            5=5
            5=2+2+1
            5=2+1+1+1
            5=1+1+1+1+1
    
    Example 2:
        Input: amount = 3, coins = [2]
        Output: 0
        Explanation: the amount of 3 cannot be made up just with coins of 2.
    
    Example 3:
        Input: amount = 10, coins = [10]
        Output: 1

    ----------------------------

    Constraints:
        - 1 <= coins.length <= 300
        - 1 <= coins[i] <= 5000
        - All the values of coins are unique.
        - 0 <= amount <= 5000
*/

class Solution {
    private int[][] dp;

    private int solve(int i, int k, int n, int[] nums) {
        if(k==0)    return 1;
        if(i>=n || k<0)    return 0;
        if(dp[i][k] != -1)  return dp[i][k];

        int take = solve(i,k-nums[i],n,nums);
        int skip = solve(i+1,k,n,nums);

        return dp[i][k] = take + skip;
    }

    public int change(int k, int[] nums) {
        int n = nums.length;
        this.dp = new int[n+1][k+1];

        for(int i=0; i<n+1; i++) {
            for(int j=0; j<k+1; j++) {
                dp[i][j] = -1;
            }
        }

        return solve(0,k,n,nums);
    }
}