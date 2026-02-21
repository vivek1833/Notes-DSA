import java.util.*;

/*
    Problem Link: https://leetcode.com/problems/decode-ways/description

    Given a string s containing only digits, return the number of ways to decode it. If the entire string cannot be decoded in any valid way, return 0.

    ----------------------------    

    Example 1:
        Input: s = "12"
        Output: 2
        Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).

    Example 2:
        Input: s = "226"
        Output: 3
        Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).

    Example 3:
        Input: s = "06"
        Output: 0
        Explanation: "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06"). In this case, the string is not a valid encoding, so return 0.

    ----------------------------
    
    Constraints:
        - 1 <= s.length <= 100
        - s contains only digits and may contain leading zero(s).
*/

class Solution {
    int[] dp;

    int solve(int idx, int n, String s) {
        if(idx==n)      return 1;
        if(idx>n)       return 0;
        if(dp[idx] != -1)   return dp[idx];

        int num1 = Integer.parseInt(String.valueOf(s.charAt(idx)));
        int curr = 0;
        if(num1>=1 && num1<=9) {
            curr = solve(idx+1, n, s);
        }

        if(idx<n-1) {
            int num2 = Integer.parseInt(String.valueOf(s.charAt(idx)))*10 
            + Integer.parseInt(String.valueOf(s.charAt(idx+1)));

            if(num2>=10 && num2<=26) {
                curr += solve(idx+2, n, s);
            }
        }

        return dp[idx] = curr;
    }

    public int numDecodings(String s) {
        int n = s.length();
        this.dp = new int[n+1];
        Arrays.fill(dp, -1);
        return solve(0, n, s);
    }
}