import java.util.*;

/*
    Problem Link: https://leetcode.com/problems/accounts-merge/description/

    Given a list of accounts where each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

    Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.

    After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

    ----------------------------

    Example 1:
        - Input: 
            accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
        Output: 
            [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
        Explanation:
            The first and second John's are the same person as they have the common email "johnsmith@mail.com".
            The third John and Mary are different people as none of their email addresses are used by other accounts.
            We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'], 
            ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
        
    Example 2:
        Input: 
            accounts = [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]
        Output: 
            [["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"],["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"],["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]]
    
    ----------------------------
    
    Constraints:
        - 1 <= accounts.length <= 1000
        - 2 <= accounts[i].length <= 10
        - 1 <= accounts[i][j].length <= 30
        - accounts[i][0] consists of English letters.
        - accounts[i][j] (for j > 0) is a valid email.
*/

class DSU {
    int[] par, size;

    DSU(int n) {
        par = new int[n+1];
        size = new int[n+1];

        for(int i=0; i<=n; i++) {
            par[i] = i;
            size[i] = 1;
        }
    }

    public int findPar(int node) {
        if(node == par[node])   return node;
        return par[node] = findPar(par[node]); 
    }

    public void union(int u, int v) {
        int pu=findPar(u), pv=findPar(v);

        if(pu == pv)    return;
        if(size[pu] > size[pv]) {
            par[pv] = pu;
            size[pu] += size[pv];
        } else {
            par[pu] = pv;
            size[pv] += size[pu];
        }
    }
}

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accs) {
        int n=accs.size();
        DSU ds = new DSU(n);
        HashMap<String,Integer> mp = new HashMap<>();
        Map<Integer, TreeSet<String>> mergedAccounts = new HashMap<>();
        List<List<String>> ans = new ArrayList<>();

        for(int i=0; i<n; i++) {
            for(int j=1; j<accs.get(i).size(); j++) {
                String email = accs.get(i).get(j);
                if(mp.containsKey(email)) {
                    ds.union(i, mp.get(email));
                } else {
                    mp.put(email, i);
                }
            }
        }
        
        for(int i=0; i<n; i++) {
            int parent = ds.findPar(i);
            mergedAccounts.putIfAbsent(parent, new TreeSet<>());
            
            List<String> account = accs.get(i);
            for(int j=1; j<account.size(); j++) {
                mergedAccounts.get(parent).add(account.get(j));
            }
        }
        
        for(Map.Entry<Integer, TreeSet<String>> entry : mergedAccounts.entrySet()) {
            int accountIndex = entry.getKey();
            TreeSet<String> emails = entry.getValue();
            
            List<String> mergedAccount = new ArrayList<>();
            mergedAccount.add(accs.get(accountIndex).get(0)); 
            mergedAccount.addAll(emails);
            
            ans.add(mergedAccount);
        }
        
        return ans;
    }
}