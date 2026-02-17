import java.util.*;

/*
    Problem Link: https://leetcode.com/problems/minimum-window-substring/

    Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".

    ------------------------------

    Example 1:
        Input: s = "ADOBECODEBANC", t = "ABC"
        Output: "BANC"
        Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
    
    Example 2:
        Input: s = "a", t = "a"
        Output: "a"
        Explanation: The entire string s is the minimum window.
    
    Example 3:
        Input: s = "a", t = "aa"
        Output: ""
        Explanation: Both 'a's from t must be included in the window. Since the largest window of s only has one 'a', return empty string.
    
    ------------------------------

    Constraints:
        m == s.length
        n == t.length
        1 <= m, n <= 105
        s and t consist of uppercase and lowercase English letters.

    Follow up: Could you find an algorithm that runs in O(m + n) time?
*/

// TC: O(N)
// SC: O(M)
class Solution {
    public String minWindow(String s, String t) 
    {
        int n=s.length(), m=t.length(), r=0, l=0, idx=-1, cnt=0, mn=Integer.MAX_VALUE;
        Map<Character, Integer> mp = new HashMap<>();

        for(int i=0; i<m; i++) {
            mp.put(t.charAt(i), mp.getOrDefault(t.charAt(i), 0)+1);
        }

        while(r<n) {
            if(mp.containsKey(s.charAt(r)) && mp.get(s.charAt(r)) > 0)    cnt++;
            mp.put(s.charAt(r), mp.getOrDefault(s.charAt(r), 0)-1);

            while(cnt==m) {
                if(r-l+1 < mn) {
                    mn = r-l+1;
                    idx = l;
                }

                mp.put(s.charAt(l), mp.getOrDefault(s.charAt(l), 0)+1);

                if(mp.containsKey(s.charAt(l)) && mp.get(s.charAt(l)) > 0)    cnt--;
                l++;
            }

            r++;
        }

        return (idx == -1 ? "" : s.substring(idx, mn));
    }
};