/*
    Problem Link: https://leetcode.com/problems/search-in-rotated-sorted-array/description

    There is an integer array nums sorted in ascending order (with distinct values).
    Prior to being passed to your function, nums is possibly left rotated at an unknown index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be left rotated by 3 indices and become [4,5,6,7,0,1,2].

    Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.

    You must write an algorithm with O(log n) runtime complexity.

    -------------------------------

    Example 1:
        Input: nums = [4,5,6,7,0,1,2], target = 0
        Output: 4
    
    Example 2:
        Input: nums = [4,5,6,7,0,1,2], target = 3
        Output: -1
    
    Example 3:
        Input: nums = [1], target = 0
        Output: -1
    Constraints:
        - 1 <= nums.length <= 5000
        - -104 <= nums[i] <= 104
        - All values of nums are unique.
        - nums is an ascending array that is possibly rotated.
        - -104 <= target <= 104
*/

// TC: O(logn)
// SC: O(1)
class Solution {
    public int search(int[] arr, int target) 
    {
        int n=arr.length, i=0, j=n-1, idx=-1;

        while(i <= j) {
            int mid = i+(j-i) / 2;

            if(arr[mid] == target)   return mid;
            
            if(arr[i] <= arr[mid]) {
                if(arr[i] <= target && target <= arr[mid])    j = mid - 1;
                else  i = mid + 1;
            }
            else {
                if(arr[j] >= target && target >= arr[mid])    i = mid + 1;
                else    j = mid - 1;
            }
        }
        
        return idx;
    }
}

// Variation if duplicates allowed
// Problem Link: https://leetcode.com/problems/search-in-rotated-sorted-array-ii/

// TC: O(logn)
// SC: O(1)
class Solution2 {
    public boolean search(int[] arr, int target) 
    {
        int n=arr.length, i=0, j=n-1;

        while(i <= j) {
            int mid = i+(j-i) / 2;

            if(arr[mid] == target)   return true;
            
            if(i<=j && arr[i]==arr[mid] && arr[mid]==arr[j]) {
                i++;
                j--;
                continue;
            }

            if(arr[i]<=arr[mid]) {
              if(arr[i]<=target && target<=arr[mid])    j=mid-1;
              else  i=mid+1;
            }
            else {
                if(arr[j]>=target && target>=arr[mid])   i=mid+1;
                else    j=mid-1;
            }
        }
        
        return false;
    }
}