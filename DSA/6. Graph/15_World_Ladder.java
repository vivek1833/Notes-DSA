import java.util.*;

/*
    Problem Link: https://leetcode.com/problems/word-ladder/description/
    Similar: https://leetcode.com/problems/word-ladder-ii/ (Give path as well)

    Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.

    --------------------------------

    Example 1:
        - Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
        - Output: 5
        - Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
    
    Example 2:
        - Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
        - Output: 0
        - Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
    
    --------------------------------

    Constraints:
        - 1 <= beginWord.length <= 10
        - endWord.length == beginWord.length
        - 1 <= wordList.length <= 5000
        - wordList[i].length == beginWord.length
        - beginWord, endWord, and wordList[i] consist of lowercase English letters.
        - beginWord != endWord
        - All the words in wordList are unique.
*/

// TC: O(N^2)
// SC: O(N)
class Node {
    public String word;
    public int step;

    public Node(String str, int step) {
        this.word = str;
        this.step = step;
    }
}

class Solution {
    public int ladderLength(String begin, String end, List<String> list) {
        int n=list.size();
        Queue<Node> q = new LinkedList<>();
        HashSet<String> st = new HashSet<>(list);

        if(!st.contains(end))    return 0;

        q.offer(new Node(begin, 0));
        st.remove(begin);

        while(!q.isEmpty()) {
            Node curr = q.poll();
            String str = curr.word;
            int step = curr.step;

            if(str.equals(end))  return step+1;
            st.remove(str);

            StringBuilder temp = new StringBuilder(str);
            for(int i=0; i<str.length(); i++) {
                char ch = str.charAt(i);

                for(char j='a'; j<='z'; j++) {
                    temp.setCharAt(i,j);
                    if(st.contains(temp.toString())) {
                        q.offer(new Node(new String(temp), step+1));
                    }
                }

                temp.setCharAt(i,ch);
            }
        }

        return 0;
    }
}