import java.util.*;

/*
    Problem Link: https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description/

    Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

    Constraints:
        - The number of nodes in the tree is in the range [0, 104].
        - -1000 <= Node.val <= 1000
*/

class Solution {
    public String serialize(TreeNode root) {
        if(root == null) return "#";

        StringBuilder s = new StringBuilder();

        String left = serialize(root.left);
        String right = serialize(root.right);

        s.append(root.val).append(",").append(left).append(",").append(right);
            System.out.println("s = " + s);

        return s.toString();
    }

    public TreeNode deserialize(String data) {
        String[] arr = data.split(",");
        index = 0;
        return build(arr);
    }

    public int index;
    
    private TreeNode build(String[] data) {
        if(Objects.equals(data[index], "#"))    {
            index++;
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(data[index]));
        index++;

        node.left = build(data);
        node.right = build(data);
        
        return node;
    }
}