import java.util.*;

/*
    Problem: Construct Binary Tree from Preorder and Inorder Traversal
    Link: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/

    Example 1:
        Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
        Output: [3,9,20,null,null,15,7]
    
    Example 2:
        Input: preorder = [-1], inorder = [-1]
        Output: [-1]
*/

class InorderAndPreorder {
    public TreeNode buildTreeFromInorderAndPreOrder(int[] preorder, int[] inorder) {
        // Map to store value -> index from inorder
        Map<Integer, Integer> inMap = new HashMap<>();

        for(int i=0; i<inorder.length; i++) {
            inMap.put(inorder[i], i);
        }

        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inMap);
    }

    private TreeNode build(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        if (preStart > preEnd || inStart > inEnd) return null;

        // First element of preorder is the root
        TreeNode root = new TreeNode(preorder[preStart]);

        int inRoot = inMap.get(root.val);
        int numsLeft = inRoot - inStart;

        root.left = build(preorder, preStart + 1, preStart + numsLeft, inorder, inStart, inRoot - 1, inMap);
        root.right = build(preorder, preStart + numsLeft + 1, preEnd, inorder, inRoot + 1, inEnd, inMap);

        return root;
    }
}

/*
    -------------------------------------------------

    Problem: Construct Binary Tree from Inorder and Postorder Traversal
    Link: https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/

    Example 1:
        Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
        Output: [3,9,20,null,null,15,7]
    
    Example 2:
        Input: inorder = [-1], postorder = [-1]
        Output: [-1]
*/

class InorderAndPostorder {
    public TreeNode buildTreeFromInorderAndPostOrder(int[] inorder, int[] postorder) {
        if (inorder.length != postorder.length) return null;

        Map<Integer, Integer> hm = new HashMap<>();
        for(int i=0; i<inorder.length; i++) {
            hm.put(inorder[i], i);
        }

        return build(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, hm);
    }

    private TreeNode build(int[] inorder, int is, int ie, int[] postorder, int ps, int pe, Map<Integer, Integer> hm) {
        if (ps > pe || is > ie) return null;

        TreeNode root = new TreeNode(postorder[pe]);
        int inRoot = hm.get(postorder[pe]);
        int numsLeft = inRoot - is;

        root.left = build(inorder, is, inRoot - 1, postorder, ps, ps + numsLeft - 1, hm);
        root.right = build(inorder, inRoot + 1, ie, postorder, ps + numsLeft, pe - 1, hm);
        
        return root;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}