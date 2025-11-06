
#include <vector>
using namespace std;
double findMedianSortedArrays(vector<int>& A, vector<int>& B){
    if(A.size()>B.size()) return findMedianSortedArrays(B,A);
    int n=A.size(), m=B.size(), half=(n+m+1)/2;
    int l=0, r=n;
    while(l<=r){
        int i=(l+r)/2;
        int j=half - i;
        int Aleft = (i? A[i-1] : INT_MIN);
        int Aright= (i<n? A[i] : INT_MAX);
        int Bleft = (j? B[j-1] : INT_MIN);
        int Bright= (j<m? B[j] : INT_MAX);
        if(Aleft<=Bright && Bleft<=Aright){
            if((n+m)%2) return max(Aleft,Bleft);
            return (max(Aleft,Bleft)+min(Aright,Bright))/2.0;
        }else if(Aleft>Bright) r=i-1;
        else l=i+1;
    }
    return 0.0;
}
