
#include <vector>
using namespace std;
int findCenter(vector<vector<int>>& edges){
    // center appears in both first two edges
    auto a=edges[0], b=edges[1];
    if(a[0]==b[0]||a[0]==b[1]) return a[0];
    return a[1];
}
