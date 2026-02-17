import java.util.*;

/*
    Problem Link: https://leetcode.com/problems/subarray-sum-equals-k

    Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
    A subarray is a contiguous non-empty sequence of elements within an array.

    -------------------------------

    Example 1:
        Input: nums = [1,1,1], k = 2
        Output: 2
    
    Example 2:
        Input: nums = [1,2,3], k = 3
        Output: 2

    -------------------------------
    
    Constraints:
        - 1 <= nums.length <= 2 * 104
        - -1000 <= nums[i] <= 1000
        - -107 <= k <= 107
*/

// TC: O(N)
// SC: O(N)
class Solution {
    public int subarraySum(int[] nums, int k) {
        int n = nums.length, ans = 0;
        int[] ps = new int[n];
        Map<Integer, Integer> mp = new HashMap<>();

        ps[0] = nums[0];
        for(int i=1; i<n; i++) {
            ps[i] = ps[i-1] + nums[i];
        }

        for(int i=0; i<n; i++) {
            if (ps[i] == k)    
                ans++;

            int curr = ps[i] - k;
            if(mp.containsKey(curr)) {
                ans += mp.get(curr);
            }

            mp.put(ps[i], mp.getOrDefault(ps[i], 0)+1);
        }

        return ans;
    }
}