import java.util.*;

/*
    Problem Link: https://leetcode.com/problems/alien-dictionary/

    ----------------------------------
    
    Example 1:
        Input: words[] = ["baa", "abcd", "abca", "cab", "cad"]
        Output: true
        Explanation: A possible corrct order of letters in the alien dictionary is "bdac".
            The pair "baa" and "abcd" suggests 'b' appears before 'a' in the alien dictionary.
            The pair "abcd" and "abca" suggests 'd' appears before 'a' in the alien dictionary.
            The pair "abca" and "cab" suggests 'a' appears before 'c' in the alien dictionary.
            The pair "cab" and "cad" suggests 'b' appears before 'd' in the alien dictionary.
            So, 'b' → 'd' → 'a' → 'c' is a valid ordering.

    Example 2:
        Input: words[] = ["caa", "aaa", "aab"]
        Output: true
        Explanation: A possible corrct order of letters in the alien dictionary is "cab".
            The pair "caa" and "aaa" suggests 'c' appears before 'a'.
            The pair "aaa" and "aab" suggests 'a' appear before 'b' in the alien dictionary. 
            So, 'c' → 'a' → 'b' is a valid ordering.
    
    Example 3:
        Input: words[] = ["ab", "cd", "ef", "ad"]
        Output: ""
        Explanation: No valid ordering of letters is possible.
            The pair "ab" and "ef" suggests "a" appears before "e".
            The pair "ef" and "ad" suggests "e" appears before "a", which contradicts the ordering rules.

    ----------------------------------

    Constraints:
        - 1 ≤ words.length ≤ 500
        - 1 ≤ words[i].length ≤ 100
        - words[i] consists only of lowercase English letters.
*/

// TC: O(N)
// SC: O(N)
class Solution {
    public String findOrder(String[] words)
    {
        HashMap<Character, ArrayList<Character>> mp = new HashMap<>();
        int[] indeg=new int[26];
        Queue<Integer> q = new LinkedList<>();
        String ans="";
        int n=words.length, cnt=0;
        Arrays.fill(indeg, -1);
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<words[i].length(); j++) {
                if(indeg[words[i].charAt(j) - 'a']==-1) 
                    cnt++;
                indeg[words[i].charAt(j) - 'a']=0;
            }
        }
        
        for(int i=1; i<n; i++) {
            
            String curr=words[i], prev=words[i-1];
            int l=Math.min(curr.length(), prev.length());
            boolean found=false;
            
            for(int j=0; j<l; j++) {
                if(curr.charAt(j)!=prev.charAt(j)) {
                    mp.get(curr.charAt(j)).add(prev.charAt(j));
                    indeg[prev.charAt(j)-'a']++;
                    found=true;
                    break;
                }
            }
            
            if(found==false && prev.length()>curr.length())
                    return ""; 
        }
        
        for(int i=0; i<26; i++) {
            if(indeg[i]==0)
                q.offer(i);
        }
        
        while(!q.isEmpty()) {
            char f = (char) (q.poll()+'a');
            ans += f;
            
            for(char neigh: mp.get(f)) {
                indeg[neigh-'a']--;
                if(indeg[neigh-'a']==0)
                    q.offer(neigh-'a');
            }
        }
        
        if(cnt==ans.length())
            return ans;
        return "";
    }
};