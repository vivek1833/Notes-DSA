/*
    Problem Link: https://leetcode.com/problems/number-of-islands/
    
    Number of Islands
*/

import java.util.*;

class CountIsland {
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    private static boolean isSafe(int i, int j, int n, int m) {
        return (i >= 0 && i < n && j >= 0 && j < m);
    }

    private static void countisland(List<List<Character>> grid, int currrow, int currcol, int row, int col) {
        grid.get(currrow).set(currcol, '0');

        for (int k = 0; k < 4; k++) {
            int x = currrow + dx[k];
            int y = currcol + dy[k];
            
            if (isSafe(x, y, row, col) && grid.get(x).get(y) == '1') {
                countisland(grid, x, y, row, col);
            }
        }
    }

    public static int numisland(List<List<Character>> grid) {
        int ans = 0;
        int row = grid.size();
        int col = grid.get(0).size();

        for (int currrow = 0; currrow < row; currrow++) {
            for (int currcol = 0; currcol < col; currcol++) {
                if (grid.get(currrow).get(currcol) == '1') {
                    ans++;
                    countisland(grid, currrow, currcol, row, col);
                }
            }
        }
        return ans;
    }
}
