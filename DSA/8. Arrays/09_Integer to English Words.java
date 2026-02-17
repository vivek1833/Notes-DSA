/*
    Problem Link: https://leetcode.com/problems/integer-to-english-words/

    Convert a non-negative integer num to its English words representation.

    -------------------------------

    Example 1:
        Input: num = 123
        Output: "One Hundred Twenty Three"

    Example 2:
        Input: num = 12345
        Output: "Twelve Thousand Three Hundred Forty Five"
    
    Example 3:
        Input: num = 1234567
        Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
    
    -------------------------------

    Constraints:
        - 0 <= num <= 231 - 1
*/

// TC: O(N)
// SC: O(1)
class Solution {
    String[] ones = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    
    String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    
    String[] thousands = {"", "Thousand ", "Million ", "Billion "};

    String solve(int grp)
    {
        if(grp==0)  return "";

        if (grp<20) 
            return ones[grp] + " ";
        else if (grp<100) 
            return tens[grp/10] + " " + solve(grp%10);
        
        return ones[grp/100] + " Hundred " + solve(grp%100);
    }

    public String numberToWords(int num) {
        if(num==0)
            return "Zero";
        
        int i=0;
        String ans="";

        while(num>0) {
            int grp=num%1000;

            if(grp!=0)    ans = solve(grp) + thousands[i] + ans;

            num/=1000;
            i++;
        }

        while(!ans.isEmpty() && ans.charAt(ans.length()-1) == ' ') 
            ans = ans.substring(0, ans.length()-1);

        return ans;
    }
}