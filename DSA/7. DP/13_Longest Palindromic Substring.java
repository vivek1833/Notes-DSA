/*
    Problem: https://leetcode.com/problems/longest-palindromic-substring/description

    Given a string s, return the longest palindromic substring in s.

    ----------------------------

    Example 1:
        Input: s = "babad"
        Output: "bab"
        Explanation: "aba" is also a valid answer.
    
    Example 2:
        Input: s = "cbbd"
        Output: "bb"
    
    ----------------------------

    Constraints:
        - 1 <= s.length <= 1000
        - s consist of only digits and English letters.
*/

class Solution {
    private String checkPalindrome(int i, int j, int n, String s) {
        while(i>=0 && j<n && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }

        return s.substring(i+1, j);
    }

    public String longestPalindrome(String s) {
        int n = s.length(), mx = Integer.MIN_VALUE;
        String ans = "";

        for(int i=0; i<n; i++) {
            String s1 = checkPalindrome(i,i,n,s);
            String s2 = checkPalindrome(i,i+1,n,s);
            String str = (s1.length()>s2.length() ? s1 : s2);
            
            if(mx < str.length()) {
                mx = str.length();
                ans = str;
            }
        }

        return ans;
    }
}