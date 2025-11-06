
#include <vector>
#include <algorithm>
using namespace std;
int leastInterval(vector<char>& tasks, int n){
    vector<int> cnt(26,0); for(char c: tasks) cnt[c-'A']++;
    int mx=*max_element(cnt.begin(), cnt.end());
    int slots=(mx-1)*(n+1);
    int numMax=count(cnt.begin(), cnt.end(), mx);
    return max((int)tasks.size(), (mx-1)*(n+1)+numMax);
}
