#include <string>
#include <vector>
using namespace std;

int lengthOfLongestSubstring(string s) {
    vector<int> last(256, -1);
    int start = 0, best = 0;
    for (int i = 0; i < (int)s.size(); ++i) {
        start = max(start, last[(unsigned char)s[i]] + 1);
        best = max(best, i - start + 1);
        last[(unsigned char)s[i]] = i;
    }
    return best;
}
