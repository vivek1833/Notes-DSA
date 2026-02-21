import java.util.*;

/*
    Problem: https://leetcode.com/problems/word-break/description

    Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.

    Note that the same word in the dictionary may be reused multiple times in the segmentation.

    ----------------------------

    Example 1:
        Input: s = "leetcode", wordDict = ["leet","code"]
        Output: true
        Explanation: Return true because "leetcode" can be segmented as "leet code".

    Example 2:
        Input: s = "applepenapple", wordDict = ["apple","pen"]
        Output: true
        Explanation: Return true because "applepenapple" can be segmented as "apple pen apple". Note that you are allowed to reuse a dictionary word.
    
    Example 3:
        Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
        Output: false
    
    ----------------------------

    Constraints:
        - 1 <= s.length <= 300
        - 1 <= wordDict.length <= 1000
        - 1 <= wordDict[i].length <= 20
        - s and wordDict[i] consist of only lowercase English letters.
        - All the strings of wordDict are unique.
*/

class Solution {
    Boolean[][] dp;

    private Boolean solve(int i, int n, String s, List<String> wordDict) {
        if(i==n)    return true;
        if(dp[i][n] != null)    return dp[i][n];

        boolean ans = false;
        for(int j=i; j<n; j++) {
            if(wordDict.contains(s.substring(i, j+1)) && solve(j+1, n, s, wordDict)) {
                ans = true;
                break;
            }
        }

        return dp[i][n] = ans;
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        this.dp = new Boolean[n+1][n+1];
        return solve(0, n, s, wordDict);
    }
}