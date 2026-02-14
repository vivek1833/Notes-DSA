/*
    Problem Link: https://leetcode.com/problems/find-the-town-judge/
    
    Find the Town Judge
*/

import java.util.*;

class Town_Judge {
    public static int judge(int n, List<List<Integer>> trust) {
        int[] indeg = new int[n + 1];
        int[] outdeg = new int[n + 1];

        for (int i = 0; i < trust.size(); i++) {
            int v1 = trust.get(i).get(0);
            int v2 = trust.get(i).get(1);
            outdeg[v1] += 1;
            indeg[v2] += 1;
        }

        for (int i = 1; i <= n; i++) {
            if (outdeg[i] == 0 && indeg[i] == n - 1) {
                return i;
            }
        }
        return -1;
    }
}
