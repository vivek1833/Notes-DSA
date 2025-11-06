#include <string>
#include <vector>
#include <climits>
using namespace std;

string minWindow(string s, string t) {
    vector<int> need(128,0);
    for (char c: t) need[c]++;
    int missing = t.size(), left = 0, start = 0, minlen = INT_MAX;
    for (int i=0; i < (int)s.size(); ++i) {
        if (need[s[i]] > 0) --missing;
        --need[s[i]];
        while (missing == 0) {
            if (i - left + 1 < minlen) { minlen = i-left+1; start = left; }
            ++need[s[left]];
            if (need[s[left]] > 0) ++missing;
            ++left;
        }
    }
    return minlen == INT_MAX ? string("") : s.substr(start, minlen);
}
