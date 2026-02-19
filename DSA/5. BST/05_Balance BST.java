import java.util.*;

/* 
    Problem: https://leetcode.com/problems/balance-a-binary-search-tree/description/

    Given the root of a binary search tree, return a balanced binary search tree with the same node values. If there is more than one answer, return any of them.

    A binary search tree is balanced if the depth of the two subtrees of every node never differs by more than 1.

    ----------------------------

    Example 1:
        Input: root = [1,null,2,null,3,null,4,null,null]
        Output: [2,1,3,null,null,null,4]
        Explanation: This is not the only correct answer, [3,1,4,null,2] is also correct.
        
    Example 2:
        Input: root = [2,1,3]
        Output: [2,1,3]

    ----------------------------

    Constraints:
        - The number of nodes in the tree is in the range [1, 104].
        - 1 <= Node.val <= 105
*/

// TC: O(N)
// SC: O(N)
class Solution {
    List<Integer> arr;

    public void dfs(TreeNode root) {
        if(root == null)        return;

        if(root.left != null)   dfs(root.left);
        arr.add(root.val);
        if(root.right != null)   dfs(root.right);
    }

    public TreeNode solve(int l, int r) {
        if(l > r)   return null;

        int mid = l+(r-l)/2;
        TreeNode node = new TreeNode(arr.get(mid));
        
        node.left = solve(l, mid-1);
        node.right = solve(mid+1, r);

        return node;
    }

    public TreeNode balanceBST(TreeNode root) {
        this.arr = new ArrayList<>();
        dfs(root);

        return solve(0, arr.size()-1);
    }
}