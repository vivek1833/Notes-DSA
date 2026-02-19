/*
    Problem Link: https://leetcode.com/problems/median-of-two-sorted-arrays/description

    Given two sorted arrays nums1 (size m) and nums2 (size n), return the median of the two sorted arrays.
    The overall time complexity must be O(log(m + n)).

    ----------------------------------------------------------
    EXAMPLES:

    Example 1:
        Input:  nums1 = [1,3], nums2 = [2]
        Output: 2.00000
        Explanation:
            Merged array = [1,2,3]
            Median = 2

    Example 2:
        Input:  nums1 = [1,2], nums2 = [3,4]
        Output: 2.50000
        Explanation:
            Merged array = [1,2,3,4]
            Median = (2 + 3) / 2 = 2.5

    ----------------------------------------------------------
    CONSTRAINTS:

        - nums1.length == m
        - nums2.length == n
        - 0 <= m <= 1000
        - 0 <= n <= 1000
        - 1 <= m + n <= 2000
        - -10^6 <= nums1[i], nums2[i] <= 10^6
*/

/*
    left: The count of elements that must exist before (or including) the median.
    m1:	  How many elements from nums1 go into the left partition
    m2:	  How many elements from nums2 go into the left partition

    Eg:
        Suppose:
        nums1 = [1, 3]
        nums2 = [2, 4, 5]

        Total = 5
            left = (5+1)/2 = 3

        So we must place exactly 3 elements in left partition. Valid partition:
            Left:  [1, 2, 3]
            Right: [4, 5]
        
        Try m1 = 1
        m2 = 4 - 1 = 3

        Left side:
            [1] from nums1
            [2,4,5] from nums2

*/

// TC: O(log(min(m,n)))
// SC: O(1)
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) 
    {
        int n=nums1.length, m=nums2.length;
        int l=0, h=n, left=(n+m+1)/2;

        if(n>m) return findMedianSortedArrays(nums2,nums1);

        while(l<=h) {
            int m1=(l+h)/2, m2=left-m1;
            int l1=Integer.MIN_VALUE, l2=Integer.MIN_VALUE;
            int u1=Integer.MAX_VALUE, u2=Integer.MAX_VALUE;

            if(m1<n)    u1=nums1[m1];
            if(m2<m)    u2=nums2[m2];
            if(m1-1>=0)    l1=nums1[m1-1];
            if(m2-1>=0)    l2=nums2[m2-1];

            if(l1<=u2 && l2<=u1) {
                if((n+m)%2==0)  return (Math.max(l1,l2) + Math.min(u1,u2))/2.0;
                else        return Math.max(l1, l2);
            }
            else if(l1>u2)  h=m1-1;
            else    l=m1+1;
        }

        return 0.0;
    }
}