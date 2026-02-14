/*
    Problem Link: https://leetcode.com/problems/is-graph-bipartite/
    
    Is Graph Bipartite?
*/

import java.util.*;

class Bipartite {
    public boolean isBipartite(int[][] mat) 
    {
        int v=mat.length;
        Queue<Integer> q = new LinkedList<>();

        int[] vis = new int[v];   // 0 1 or 2, 0 not vis

        for(int i=0;i<v;i++) {
            if(vis[i]==0) {
                q.offer(i);
                vis[i]=1;

                while(!q.isEmpty()) {
                    int n=q.size();

                    while(n-- > 0) {    
                        int f=q.poll();

                        for(int neigh: mat[f]) {
                            if(vis[neigh]==0) {
                                q.offer(neigh);
                                vis[neigh]=(vis[f]==1 ? 2 : 1);
                            } else if(vis[neigh]==vis[f]) {
                                return false;
                            }
                        }
                    }
                }
            }
        }

        return true;
    }
};