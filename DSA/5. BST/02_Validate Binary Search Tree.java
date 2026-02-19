/*
    Problem: https://leetcode.com/problems/validate-binary-search-tree/description/

    Given the root of a binary tree, determine if it is a valid binary search tree (BST).

    Constraints:
        - The number of nodes in the tree is in the range [1, 104].
        - -231 <= Node.val <= 231 - 1
*/

// TC: O(N)
// SC: O(N)
class Solution {
    private boolean solve(long mn, long mx, TreeNode root)
    {
        if(root == null)
            return true;

        if(root.val >= mx || root.val <= mn)
            return false;
        
        boolean l = solve(mn, root.val, root.left);
        boolean r = solve(root.val, mx, root.right);

        return l && r;
    }

    public boolean isValidBST(TreeNode root) 
    {
        if(root == null)
            return true;

        return solve(Long.MIN_VALUE, Long.MAX_VALUE, root);
    }
}