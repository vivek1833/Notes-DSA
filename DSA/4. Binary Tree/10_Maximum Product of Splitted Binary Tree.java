/*
    Problem Link: https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/

    Given the root of a binary tree, split the binary tree into two subtrees by removing one edge such that the product of the sums of the subtrees is maximized.

    Return the maximum product of the sums of the two subtrees. Since the answer may be too large, return it modulo 109 + 7.

    Example 1:
        Input: root = [1,2,3,4,5,6]
        Output: 110
        Explanation: Remove the red edge and get 2 binary trees with sum 11 and 10. Their product is 110 (11*10)
    
    Example 2:
        Input: root = [1,null,2,3,4,null,null,5,6]
        Output: 90
        Explanation: Remove the red edge and get 2 binary trees with sum 15 and 6.Their product is 90 (15*6)

    Constraints:
        - The number of nodes in the tree is in the range [2, 5 * 104].
        - 1 <= Node.val <= 104

*/

class Solution {
    private static final long MOD = 1000000007;
    private long mx = Integer.MIN_VALUE;

    private long getSum(TreeNode root) {
        if(root == null)    return 0;
        return getSum(root.left) + getSum(root.right) + root.val;
    }

    public long solve(TreeNode root, long sum) {
        if(root == null)    return 0;

        long lt = solve(root.left, sum);
        long rt = solve(root.right, sum);

        long curr = (lt + rt + root.val);
        mx = Math.max(mx, curr*(sum-curr));

        return curr;
    }

    public int maxProduct(TreeNode root) {
        long sum = getSum(root);
        solve(root, sum);
        return (int) (mx % MOD);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { this.val = val; }
}