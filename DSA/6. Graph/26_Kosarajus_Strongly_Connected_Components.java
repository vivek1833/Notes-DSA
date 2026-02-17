import java.util.*;

/*
    Problem Link: https://www.geeksforgeeks.org/problems/strongly-connected-components-kosarajus-algo/1

    You are given an unweighted directed graph having 'V' vertices and 'E' edges. Your task is to count the number of strongly connected components (SCCs) present in the graph.
    A directed graph is said to be strongly connected if every vertex is reachable from every other vertex. The strongly connected components of a graph are the subgraphs which are themselves strongly connected.

    Intution:
        - The idea is to find all the strongly connected components in the graph and return the count of them.
        - To find the strongly connected components, we can use Kosaraju's algorithm.
        - Kosaraju's algorithm is a two-pass algorithm.
        - In the first pass, we find all the strongly connected components and store them in a stack.
        - In the second pass, we traverse the stack and check if the vertices are strongly connected or not.
        - If the vertices are strongly connected, we increment the count.

    TC: O(V + E)
    SC: O(V + E)
*/

class Solution {
    static List<List<Integer>> adj;
    static List<List<Integer>> adjT;
    static Stack<Integer> st;
    static boolean[] vis;

    static void solve(int node) {
        vis[node] = true;

        for(int neigh: adj.get(node)) {
            if(!vis[neigh]) {
                solve(neigh);
            }
        }

        st.push(node);
    }

	static void dfs(int node) {
        vis[node] = true;

        for(int neigh: adjT.get(node)) {
            if(!vis[neigh]) {
                dfs(neigh);
            }
        }
    }

    public static int stronglyConnectedComponents(int ver,
            ArrayList<ArrayList<Integer>> edges)
    {
        adj = new ArrayList<>();
        adjT = new ArrayList<>();
        st = new Stack<>();
        vis = new boolean[ver];
		int cnt = 0;

        for(int i=0; i<ver; i++) {
            vis[i]=false;
            adj.add(new ArrayList<>());
            adjT.add(new ArrayList<>());
        }

        for(ArrayList<Integer> edge: edges) {
            int u=edge.get(0), v=edge.get(1);
            adj.get(u).add(v);
            adjT.get(v).add(u);
        }

        for(int i=0; i<ver; i++) {
            if(!vis[i]) {
                solve(i);
            }
        }

		Arrays.fill(vis, false);

		while(!st.isEmpty()) {
			int node = st.pop();

            if(!vis[node]) {
                dfs(node);
				cnt++;
            }
        }

		return cnt;
    }
}