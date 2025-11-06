
// Placeholder: median-of-medians selection is complex; use nth_element in practice.
#include <vector>
using namespace std;
int selectKth(vector<int>& a, int k){ nth_element(a.begin(), a.begin()+k, a.end()); return a[k]; }
