import java.util.*;

/*
    Problem: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/

    Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

    According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

    ---------------------------------    

    Example 1:
        Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
        Output: 3
        Explanation: The LCA of nodes 5 and 1 is 3.
    
    Example 2:
        Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
        Output: 5
        Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
    
    Example 3:
        Input: root = [1,2], p = 1, q = 2
        Output: 1

    ---------------------------------

    Constraints:
        - The number of nodes in the tree is in the range [2, 105].
        - -109 <= Node.val <= 109
        - All Node.val are unique.
        - p != q
        - p and q will exist in the tree.
*/

class Solution {
    public TreeNode LCA(TreeNode root, TreeNode p, TreeNode q) 
    {
        if(root==null)  return null;
        if(root.val==p.val || root.val==q.val) return root;

        TreeNode l=LCA(root.left,p,q);
        TreeNode r=LCA(root.right,p,q);

        if(l!=null && r!=null)  return root;
        if(l==null && r!=null)  return r;
        if(l!=null && r==null)  return l;
        return null;
    }
}

/*
    Problem : https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/description/

    Given the root of a binary tree, the value of a target node target, and an integer k, return an array of the values of all nodes that have a distance k from the target node.
*/

class Solution2 {
    HashMap<TreeNode, TreeNode> map;

    private void solve(TreeNode node, TreeNode parent) {
        if(node == null) return;
        
        map.put(node, parent);
        solve(node.left, node);
        solve(node.right, node);
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> ans = new ArrayList<>();
        this.map = new HashMap<>();
        Queue<TreeNode> q = new LinkedList<>();
        HashMap<TreeNode, Boolean> vis = new HashMap<>();

        if (root == null) return ans;
        
        solve(root, null);
        
        q.offer(target);
        vis.put(target, true);
        int level=0;
        
        while (!q.isEmpty()) {
            int size = q.size();
            
            if(level==k) {
                for(int i=0; i<size; i++) {
                    ans.add(q.poll().val);
                }
                return ans;
            }
            
            while(size-- > 0) {
                TreeNode curr = q.poll();
                vis.put(curr, true);
                
                if(curr.left!=null && !vis.getOrDefault(curr.left, false))    q.offer(curr.left);
                if(curr.right!=null && !vis.getOrDefault(curr.right, false))    q.offer(curr.right);
                if(map.get(curr)!=null && !vis.getOrDefault(map.get(curr), false))    q.offer(map.get(curr));
            }
            
            level++;
        }
        
        return ans;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}