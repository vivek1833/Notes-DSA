import java.util.*;

/* 
    Problem Link: https://www.geeksforgeeks.org/problems/longest-common-substring1452/1

    You are given two strings s1 and s2. Your task is to find the length of the longest common substring among the given strings.

    ----------------------------

    Example 1:
        Input: s1 = "ABCDGH", s2 = "ACDGHR"
        Output: 4
        Explanation: The longest common substring is "CDGH" with a length of 4.

    Example 2:
        Input: s1 = "abc", s2 = "acb"
        Output: 1
        Explanation: The longest common substrings are "a", "b", "c" all having length 1.
    
    Example 3:
        Input: s1 = "YZ", s2 = "yz"
        Output: 0
    
    ----------------------------

    Constraints:
        - 1 <= s1.size(), s2.size() <= 103
        - Both strings may contain upper and lower case alphabets.
*/
 
class Solution {
    int[][] dp;
    int mx = 0;

    private int solve(int i, int j, String s1, String s2) {
        if(i<0 || j<0) return 0;
        if(dp[i][j] != -1) return dp[i][j];
        
        int cnt = 0;
        
        if(s1.charAt(i) == s2.charAt(j)) {
            cnt = 1 + solve(i-1, j-1, s1, s2);
            mx = Math.max(mx, cnt);
        }
        
        solve(i-1, j, s1, s2);
        solve(i, j-1, s1, s2);
        
        return dp[i][j] = cnt;
    }

    public int longCommSubstr(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        this.dp = new int[n][m];
        this.mx = 0;
        
        for(int[] row : dp) {
            Arrays.fill(row, -1);
        }

        solve(n-1, m-1, s1, s2);
        return mx;
    }
}