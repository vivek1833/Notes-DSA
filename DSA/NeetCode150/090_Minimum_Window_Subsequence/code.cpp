
#include <string>
using namespace std;
string minWindow(string S, string T){
    int n=S.size(), m=T.size();
    int best=INT_MAX, bi=-1;
    for(int i=0;i<n;++i){
        if(S[i]==T[0]){
            int si=i, ti=0;
            while(si<n && ti<m){ if(S[si]==T[ti]) ++ti; ++si; }
            if(ti==m){
                int end=si-1;
                ti=m-1;
                while(si>i){ if(S[si-1]==T[ti]) --ti; --si; }
                if(end-si+1 < best){ best = end-si+1; bi=si; }
            }
        }
    }
    return bi==-1? string("") : S.substr(bi,best);
}
