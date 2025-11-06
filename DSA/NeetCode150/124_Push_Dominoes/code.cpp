
#include <string>
#include <vector>
using namespace std;
string pushDominoes(string s){
    int n=s.size(); vector<int> force(n,0);
    for(int i=0,f=0;i<n;i++){ f = s[i]=='R'? n: s[i]=='L'?0: max(f-1,0); force[i]+=f; }
    for(int i=n-1,f=0;i>=0;i--){ f = s[i]=='L'? n: s[i]=='R'?0: max(f-1,0); force[i]-=f; }
    string res=s;
    for(int i=0;i<n;i++) res[i] = force[i]==0?'.':(force[i]>0?'R':'L');
    return res;
}
