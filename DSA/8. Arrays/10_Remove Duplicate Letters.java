import java.util.*;

/*
    Problem Link: https://leetcode.com/problems/remove-duplicate-letters

    Given a string s, remove duplicate letters so that every letter appears once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.

    ----------------------------

    Example 1:
        Input: s = "bcabc"
        Output: "abc"
    
    Example 2:
        Input: s = "cbacdcbc"
        Output: "acdb"
    
    ----------------------------

    Constraints:
        - 1 <= s.length <= 104
        - s consists of lowercase English letters.
*/

// TC: O(N)
// SC: O(N)
class Solution {
    public String removeDuplicateLetters(String s) {
        int n=s.length();
        Stack<Character> st = new Stack<>();
        int[] freq = new int[26];
        boolean[] vis = new boolean[26];

        for(int i=0; i<26; i++) {
            freq[i]=0;
            vis[i]=false;
        }

        for(int i=0; i<n; i++) {
            freq[s.charAt(i)-'a']++;
        }

        for(int i=0; i<n; i++) {
            if(vis[s.charAt(i)-'a']) {
                freq[s.charAt(i)-'a']--;
                continue;
            } else if(st.isEmpty()) {
                st.push(s.charAt(i));
                freq[s.charAt(i)-'a']--;
                vis[s.charAt(i)-'a']=true;
            } else {
                while(!st.isEmpty() && freq[st.peek()-'a']>0 && st.peek()>s.charAt(i)) {
                    vis[st.peek()-'a']=false;
                    st.pop();
                }

                st.push(s.charAt(i));
                vis[s.charAt(i)-'a']=true;
                freq[s.charAt(i)-'a']--;
            }
        }

        StringBuilder ans = new StringBuilder();
        while(!st.isEmpty()) {
            ans.append(st.peek());
            st.pop();
        }
        
        ans.reverse();
        return ans.toString();
    }
}