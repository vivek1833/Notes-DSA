import java.util.*;

/*
    Given a binary tree where node values are digits from 1 to 9. A path in the binary tree is said to be pseudo-palindromic if at least one permutation of the node values in the path is a palindrome.

    Return the number of pseudo-palindromic paths going from the root node to leaf nodes.


    Example 1:
        Input: root = [2,3,1,3,1,null,1]
        Output: 2 
        Explanation: The figure above represents the given binary tree. There are three paths going from the root node to leaf nodes: the red path [2,3,3], the green path [2,1,1], and the path [2,3,1]. Among these paths only red path and green path are pseudo-palindromic paths since the red path [2,3,3] can be rearranged in [3,2,3] (palindrome) and the green path [2,1,1] can be rearranged in [1,2,1] (palindrome).

    Example 2:
        Input: root = [2,1,1,1,3,null,null,null,null,null,1]
        Output: 1 
        Explanation: The figure above represents the given binary tree. There are three paths going from the root node to leaf nodes: the green path [2,1,1], the path [2,1,3,1], and the path [2,1]. Among these paths only the green path is pseudo-palindromic since [2,1,1] can be rearranged in [1,2,1] (palindrome).

    Example 3:
    Input: root = [9]
    Output: 1

    Constraints:
        - The number of nodes in the tree is in the range [1, 105].
        - 1 <= Node.val <= 9
*/

class Solution {
    int cnt;
    HashMap<Integer,Integer> mp;
    
    private Boolean check() {
        int odd = 0;

        for(Map.Entry<Integer,Integer> it : mp.entrySet()) {
            if(it.getValue()%2 != 0) {
                odd++;
            }
        }
        
        return odd<=1;
    }

    private void solve(TreeNode root) {
        if(root==null)  return;

        this.mp.put(root.val, this.mp.getOrDefault(root.val, 0)+1);
        
        solve(root.left);
        solve(root.right);

        if(root.left==null && root.right==null) {
            if(check())  cnt++;
        }

        this.mp.put(root.val, this.mp.get(root.val)-1);
    }

    public int pseudoPalindromicPaths (TreeNode root) {
        this.mp = new HashMap<>();
        this.cnt = 0;
        solve(root);
        return this.cnt;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { this.val = val; }
}