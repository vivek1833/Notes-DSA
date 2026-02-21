/* 
    Problem Link: https://leetcode.com/problems/longest-common-subsequence/

    Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.

    A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

    For example, "ace" is a subsequence of "abcde".
    A common subsequence of two strings is a subsequence that is common to both strings.

    ----------------------------

    Example 1:
        Input: text1 = "abcde", text2 = "ace" 
        Output: 3  
        Explanation: The longest common subsequence is "ace" and its length is 3.
    
    Example 2:
        Input: text1 = "abc", text2 = "abc"
        Output: 3
        Explanation: The longest common subsequence is "abc" and its length is 3.
    
    Example 3:
        Input: text1 = "abc", text2 = "def"
        Output: 0
        Explanation: There is no such common subsequence, so the result is 0.
    
    ----------------------------

    Constraints:
        - 1 <= text1.length, text2.length <= 1000
        - text1 and text2 consist of only lowercase English characters.
*/

class Solution {
    int[][] dp;

    int solve(int n, int m, String text1, String text2) {
        if(m==0 || n==0)    return 0;
        if(dp[n][m] != -1)  return dp[m][n];

        if(text1.charAt(n-1) == text2.charAt(m - 1))
            dp[n][m] = 1 + solve(n-1, m-1, text1, text2);
        else
            dp[n][m] = Math.max(
                solve(n-1, m, text1, text2),
                solve(n, m-1, text1, text2)
            );

        return dp[n][m];
    }

    public int LCS(String text1, String text2) {
        int n = text1.length(), m = text2.length();
        this.dp = new int[n+1][m+1];

        for(int i=0; i<n+1; i++) {
            for(int j=0; j<m+1; j++) {
                dp[i][j] = -1;
            }
        }

        return solve(n, m, text1, text2);
    }
}
