import java.util.*;

/*
    Problem: https://leetcode.com/problems/cheapest-flights-within-k-stops/

    There are n cities connected by some number of flights. You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.

    You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.

    -------------------------------------

    Example 1:
        - Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
        - Output: 700
        - Explanation:
            The graph is shown above.
            The optimal path with at most 1 stop from city 0 to 3 is marked in red and has cost 100 + 600 = 700.
            Note that the path through cities [0,1,2,3] is cheaper but is invalid because it uses 2 stops.


    Example 2:
        - Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
        - Output: 200
        - Explanation:
            The graph is shown above.
            The optimal path with at most 1 stop from city 0 to 2 is marked in red and has cost 100 + 100 = 200.
            Note that the path through city 0 is cheaper but is invalid because it uses 2 stops.
    
    Example 3:
        - Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
        - Output: 500
        Explanation:
            The graph is shown above.
            The optimal path with no stops from city 0 to 2 is marked in red and has cost 500.

*/

// TC: O(V+E)
// SC: O(V+E)
class Pair {
    int node, cost, step;

    Pair(int node, int cost, int step) {
        this.node = node;
        this.cost = cost;
        this.step = step;
    }
}

class Solution {
    public int findCheapestPrice(int n, int[][] mat, int src, int dest, int k) {
        List<List<int[]>> adj = new ArrayList<>();  // node, cost
        Queue<Pair> q = new LinkedList<>();
        int[] dist = new int[n];

        for(int i=0; i<n; i++) {
            adj.add(new ArrayList<>());
            dist[i] = Integer.MAX_VALUE;
        }

        for(int[] it: mat) {
            int u=it[0], v=it[1], w=it[2];
            adj.get(u).add(new int[]{v,w});
        }

        q.offer(new Pair(src,0,0));
        dist[src]=0;

        while(!q.isEmpty()) {
            Pair p = q.poll();
            int node = p.node, step = p.step, cost = p.cost;

            if(step > k)    continue;

            for(int[] neigh: adj.get(node)) {
                
                if(dist[neigh[0]] > cost + neigh[1]) {
                    dist[neigh[0]] = cost + neigh[1];
                    q.offer(new Pair(neigh[0], dist[neigh[0]], step+1));
                }
            }
        }

        return (dist[dest]==Integer.MAX_VALUE ? -1 : dist[dest]);
    }
}