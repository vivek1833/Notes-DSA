
#include <string>
using namespace std;
struct WN{ bool end=false; WN* nxt[26]={0}; };
class WordDictionary{
    WN* root;
    bool dfs(string &w, int i, WN* n){
        if(!n) return false;
        if(i==(int)w.size()) return n->end;
        if(w[i]=='.'){
            for(int k=0;k<26;++k) if(dfs(w,i+1,n->nxt[k])) return true;
            return false;
        }else{
            return dfs(w,i+1,n->nxt[w[i]-'a']);
        }
    }
public:
    WordDictionary(){ root=new WN(); }
    void addWord(string w){ WN* cur=root; for(char c: w){ int i=c-'a'; if(!cur->nxt[i]) cur->nxt[i]=new WN(); cur=cur->nxt[i]; } cur->end=true; }
    bool search(string w){ return dfs(w,0,root); }
};
