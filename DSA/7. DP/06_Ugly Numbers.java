/*
    Problem: https://leetcode.com/problems/ugly-number-ii/description/

    An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.
    Given an integer n, return the nth ugly number.

    ----------------------------

    Example 1:
        Input: n = 10
        Output: 12
        Explanation: [1, 2, 3, 4, 5, 6, 8, 9, 10, 12] is the sequence of the first 10 ugly numbers.
    
    Example 2:
        Input: n = 1
        Output: 1
        Explanation: 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.
    
    ----------------------------

    Constraints:
        - 1 <= n <= 1690
*/

// TC: O(n)
// SC: O(n)
class Solution {
    public int nthUglyNumber(int n) {
        int x = 0, y = 0, z = 0;
        int[] dp = new int[n];
        dp[0] = 1;

        for(int i=1; i<n; i++) {
            int nx = 2*dp[x];
            int ny = 3*dp[y];
            int nz = 5*dp[z];

            int mn = Math.min(nx, Math.min(ny, nz));
            dp[i] = mn;

            if(nx == mn)    x++;
            if(ny == mn)    y++;
            if(nz == mn)    z++;
        }

        return dp[n-1];
    }
}