import java.util.*;


// Top View
class TopView {
    public ArrayList<Integer> topView(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        Queue<Pair> q = new LinkedList<>();        // node, col
        Map<Integer, Integer> mp = new TreeMap<>();

        q.offer(new Pair(root, 0));
        mp.put(0, root.val);

        while(!q.isEmpty()) {
            Pair p = q.poll();
            TreeNode node = p.node;
            int col = p.col;

            if(!mp.containsKey(col))    mp.put(col, node.val);

            if(node.left != null)    q.offer(new Pair(node.left, col-1));
            if(node.right != null)    q.offer(new Pair(node.right, col+1));
        }
        
        for(Map.Entry<Integer, Integer> it: mp.entrySet()) {
            res.add(it.getValue());
        }

        return res;
    }
}

// Bottom View
class BottomView {
    public ArrayList<Integer> bottomView(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        Queue<Pair> q = new LinkedList<>();        // node, col
        Map<Integer, Integer> mp = new TreeMap<>();

        q.offer(new Pair(root, 0));
        mp.put(0, root.val);

        while(!q.isEmpty()) {
            Pair p = q.poll();
            TreeNode node = p.node;
            int col = p.col;

            mp.put(col, node.val);

            if(node.left != null)    q.offer(new Pair(node.left, col-1));
            if(node.right != null)    q.offer(new Pair(node.right, col+1));
        }
        
        for(Map.Entry<Integer, Integer> it: mp.entrySet()) {
            res.add(it.getValue());
        }

        return res;
    }
}

// Left View
class LeftView {
    public ArrayList<Integer> leftView(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();        
        q.offer(root);

        while(!q.isEmpty()) {
            int n = q.size();

            for(int i=0; i<n; i++) {
                TreeNode node = q.poll();

                if(i==0)    res.add(node.val);
                if(node.left != null)    q.offer(node.left);
                if(node.right != null)    q.offer(node.right);
            }
        }
        
        return res;
    }
}
     
// Right View
class RightView {
    public ArrayList<Integer> rightView(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();        
        q.offer(root);

        while(!q.isEmpty()) {
            int n = q.size();

            for(int i=0; i<n; i++) {
                TreeNode node = q.poll();

                if(i==n-1)    res.add(node.val);
                if(node.left != null)    q.offer(node.left);
                if(node.right != null)    q.offer(node.right);
            }
        }
        
        return res;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Pair {
    TreeNode node;
    int col;
    Pair(TreeNode node, int col) {
        this.node = node;
        this.col = col;
    }
}