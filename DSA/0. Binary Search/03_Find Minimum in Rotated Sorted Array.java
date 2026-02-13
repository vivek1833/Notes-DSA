/*
    Problem Link: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/

    Example 1:
        Input: nums = [3,4,5,1,2]
        Output: 1
        Explanation: The original array was [1,2,3,4,5] rotated 3 times.
    
    Example 2:
        Input: nums = [4,5,6,7,0,1,2]
        Output: 0
        Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
    
    Example 3:
        Input: nums = [11,13,15,17]
        Output: 11
        Explanation: The original array was [11,13,15,17] and it was rotated 4 times.

    Constraints:
    - n == nums.length
    - 1 <= n <= 5000
    - -5000 <= nums[i] <= 5000
    - All the integers of nums are unique.
    - nums is sorted and rotated between 1 and n times.
*/

/*
    - The minimum will be rotating point, if we find that that will be out answer. 
    - If start > Mid (8 > 6) we know that turning point is between these hence move to left.
    - If Start < Mid (6 < 8), means this part is increasing, we might get turning point after Mid, so move right
*/

// TC: O(logn)
// SC: O(1)
class Solution {
    public int findMin(int[] arr) 
    {
        int n=arr.length, i=0, j=n-1, ans=Integer.MAX_VALUE;

        while(i<=j) {
            int mid=i+(j-i)/2;

            if(arr[i]<=arr[mid]) {
                ans=Math.min(ans,arr[i]);
                i=mid+1;
            }
            else {
                ans=Math.min(ans,arr[mid]);
                j=mid-1;
            }
        }   

        return ans;
    }
};