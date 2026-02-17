import java.util.*;

/*
    Problem Link: https://leetcode.com/problems/critical-connections-in-a-network/

    There are n servers numbered from 0 to n - 1 connected by undirected server-to-server connections forming a network where connections[i] = [ai, bi] represents a connection between servers ai and bi. Any server can reach other servers directly or indirectly through the network. Return all critical connections in the network in any order.

    A critical connection is a connection that, if removed, will make some servers unable to reach some other server.

    ----------------------------------

    Example 1: 
        Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
        Output: [[1,3]]
        Explanation: [[3,1]] is also accepted.
        
    Example 2:
        Input: n = 2, connections = [[0,1]]
        Output: [[0,1]]

    ----------------------------------

    Constraints:
        - 2 <= n <= 105
        - n - 1 <= connections.length <= 105
        - 0 <= ai, bi <= n - 1
        - ai != bi
        - There are no repeated connections.
*/

// TC: O(V+E)
// SC: O(V+E)
class Solution {
    List<List<Integer>> adj;
    boolean[] vis;
    int[] low;
    int[] tin;
    List<List<Integer>> ans;
    int timer = 0;

    public void dfs(int node, int parent) {
        vis[node] = true;
        low[node] = tin[node] = timer;
        timer++;

        for(int neigh: adj.get(node)) {
            if(neigh == parent) continue;

            if(!vis[neigh]) {
                dfs(neigh,node);

                if(low[neigh] > tin[node]) {
                    ans.add(new ArrayList<>(Arrays.asList(neigh,node)));
                }
            }

            low[node] = Math.min(low[node], low[neigh]);
        }
    }

    public List<List<Integer>> criticalConnections(int ver, List<List<Integer>> edges) {
        this.ans = new ArrayList<>();
        this.adj = new ArrayList<>();
        this.vis = new boolean[ver];
        this.low = new int[ver];
        this.tin = new int[ver];

        for(int i=0; i<ver; i++) {
            vis[i] = false;
            low[i] = 0;
            tin[i] = i;
            adj.add(new ArrayList<>());
        }

        for(List<Integer> edge: edges) {
            int u = edge.get(0), v = edge.get(1);
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        for(int i=0; i<ver; i++) {
            if(!vis[i]) {
                dfs(i,-1);
            }
        }

        return ans;
    }
}