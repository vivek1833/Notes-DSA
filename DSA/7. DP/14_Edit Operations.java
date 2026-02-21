/*
    Problem: https://leetcode.com/problems/edit-distance/description

    Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.

    You have the following three operations permitted on a word:

    Insert a character
    Delete a character
    Replace a character
    
    ---------------------------

    Example 1:
        Input: word1 = "horse", word2 = "ros"
        Output: 3
        Explanation: 
            horse -> rorse (replace 'h' with 'r')
            rorse -> rose (remove 'r')
            rose -> ros (remove 'e')
            
    Example 2:
        Input: word1 = "intention", word2 = "execution"
        Output: 5
        Explanation: 
            intention -> inention (remove 't')
            inention -> enention (replace 'i' with 'e')
            enention -> exention (replace 'n' with 'x')
            exention -> exection (replace 'n' with 'c')
            exection -> execution (insert 'u')

    ----------------------------

    Constraints:
        - 0 <= word1.length, word2.length <= 500
        - word1 and word2 consist of lowercase English letters.
*/

class Solution {
    private int solve(int i, int j, int n, int m, String x, String y, int[][] dp) {
        if(i>=n)    return m-j;
        if(j>=m)    return n-i;
        if(dp[i][j]!=-1)    return dp[i][j];

        if(x.charAt(i)==y.charAt(j)) {
            return dp[i][j] = solve(i+1, j+1, n, m, x, y, dp);
        }

        int one = solve(i, j+1, n, m, x, y, dp);
        int two = solve(i+1, j, n, m, x, y, dp);
        int three = solve(i+1, j+1, n, m, x, y, dp);

        return dp[i][j] = Math.min(one, Math.min(two, three))+1;
    }

    public int minDistance(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        int[][] dp = new int[n+1][m+1];

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                dp[i][j]=-1;
            }
        }

        return solve(0,0,n,m,word1,word2, dp);
    }
}