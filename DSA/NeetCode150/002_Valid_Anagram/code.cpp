#include <string>
#include <vector>
#include <algorithm>
using namespace std;

bool isAnagram(string s, string t) {
    if (s.size() != t.size()) return false;
    vector<int> cnt(26,0);
    for (char c: s) cnt[c-'a']++;
    for (char c: t) cnt[c-'a']--;
    for (int x: cnt) if (x) return false;
    return true;
}
