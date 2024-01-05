// https://www.geeksforgeeks.org/problems/subarray-with-0-sum-1587115621/1

class Solution{
public:
    bool subArrayExists(int arr[], int n)
    {
        unordered_map<int,int> mp;
        int sum=0;
        
        /* if the sum value is repeated means the subarray can be made 0
            Ex: 4 2 -3 1 6
            mp = {
                4:2
                6:1
                3:1
                10:1
            }
            value 4 is repeated means subarray 0 can be formed
        */
        
        for(int i=0; i<n; i++) {
            sum+=arr[i];
            mp[sum]++;
            if(mp[sum]>1 || sum==0)    return true;
        }
        
        return false;
    }
};