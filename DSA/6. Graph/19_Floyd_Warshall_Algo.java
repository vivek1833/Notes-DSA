import java.util.*;

/*
    Problem Link: https://www.geeksforgeeks.org/problems/implementing-floyd-warshall2042/1
    
    Floyd-Warshall Algorithm - All Pairs Shortest Path
*/


// TC: O(n^3)
// SC: O(1)
class Solution {
    public void floydWarshall(int[][] dist) {
        int n = dist.length;

        for(int k=0; k<n; k++) {
            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(dist[i][j] == Integer.MAX_VALUE)  dist[i][j] = -1;
            }
        }
    }
}