/*
    Problem Link: https://leetcode.com/problems/flood-fill/
    
    Flood Fill Algorithm
*/

import java.util.*;

class FloodFiller {
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    private static boolean isSafe(int i, int j, int n, int m) {
        return (i >= 0 && i < n && j >= 0 && j < m);
    }

    private static void flood(List<List<Integer>> image, int i, int j, int m, int n, int currcolor, int newcolor) {
        image.get(i).set(j, newcolor);

        for (int k = 0; k < 4; k++) {
            int x = i + dx[k];
            int y = j + dy[k];
            
            if (isSafe(x, y, n, m) && image.get(x).get(y) == currcolor) {
                flood(image, x, y, m, n, currcolor, newcolor);
            }
        }
    }        

    public static List<List<Integer>> floodfill(List<List<Integer>> image, int sr, int sc, int newcolor) {
        int m = image.size();
        int n = image.get(0).size();
        int currcolor = image.get(sr).get(sc);
        flood(image, sr, sc, m, n, currcolor, newcolor);
        return image;
    }
}
