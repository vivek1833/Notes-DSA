#include<bits/stdc++.h>
using namespace std;
 
// Problem: https://practice.geeksforgeeks.org/problems/first-and-last-occurrences-of-x/0
class Solution {
private:
    int BS(int arr[], int i, int j, int x, char t)
    {
        int res=-1;
        while(i<=j) {
            int mid=(i+j)/2;
            if(arr[mid]==x) {
                res=mid;
                if(t=='f')  j=mid-1;
                else    i=mid+1;
            }
            else if(arr[mid]<x)    i=mid+1;
            else    j=mid-1;
        }
        
        return res;
    }
    
public:
    vector<int> find(int arr[], int n , int x )
    {
        int f, l;
        f=BS(arr,0,n-1,x,'f');
        l=BS(arr,0,n-1,x,'l');
        return {f,l};
    }
};