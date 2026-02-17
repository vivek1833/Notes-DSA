/*
    Problem Link: https://leetcode.com/problems/next-permutation/
    
    The next permutation of an array of integers is the next lexicographically greater permutation of its integer.
    For example, the next permutation of arr = [1,2,3] is [1,3,2].
    Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
    While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger rearrangement.
    Given an array of integers nums, find the next permutation of nums.
    The replacement must be in place and use only constant extra memory.

    ------------------------------

    Example 1:
        Input: nums = [1,2,3]
        Output: [1,3,2]
    
    Example 2:
        Input: nums = [3,2,1]
        Output: [1,2,3]
    
    Example 3:
        Input: nums = [1,1,5]
        Output: [1,5,1]
    
    -----------------------------

    Constraints:
        - 1 <= nums.length <= 100
        - 0 <= nums[i] <= 100

*/

// TC: O(n)
// SC: O(1)
class Solution {
    private void reverse(int start, int end, int[] nums) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
    
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int dip = -1;

        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                dip = i;
                break;
            }
        }

        if (dip == -1) {
            reverse(0, n-1, nums);
            return;
        }

        for (int i = n-1; i >= dip; i--) {
            if (nums[i] > nums[dip]) {
                int temp = nums[i];
                nums[i] = nums[dip];
                nums[dip] = temp;
                break;
            }
        }
        
        reverse(dip+1, n-1, nums);
    }
}