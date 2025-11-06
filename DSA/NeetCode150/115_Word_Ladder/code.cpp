
#include <unordered_set>
#include <queue>
#include <string>
using namespace std;
int ladderLength(string beginWord, string endWord, vector<string>& wordList){
    unordered_set<string> dict(wordList.begin(), wordList.end());
    if(!dict.count(endWord)) return 0;
    queue<pair<string,int>> q; q.push({beginWord,1});
    while(!q.empty()){
        auto [w,d]=q.front(); q.pop();
        if(w==endWord) return d;
        for(int i=0;i<(int)w.size();++i){
            string s=w;
            for(char c='a'; c<='z'; ++c){
                s[i]=c;
                if(dict.count(s)){ dict.erase(s); q.push({s,d+1}); }
            }
        }
    }
    return 0;
}
