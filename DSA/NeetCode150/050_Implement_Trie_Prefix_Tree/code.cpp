
#include <string>
#include <vector>
using namespace std;
struct Node{ bool end=false; Node* nxt[26]={0}; };
class Trie{
    Node* root;
public:
    Trie(){ root=new Node(); }
    void insert(string w){ Node* cur=root; for(char c: w){ int i=c-'a'; if(!cur->nxt[i]) cur->nxt[i]=new Node(); cur=cur->nxt[i]; } cur->end=true; }
    bool search(string w){ Node* cur=root; for(char c: w){ int i=c-'a'; if(!cur->nxt[i]) return false; cur=cur->nxt[i]; } return cur->end; }
    bool startsWith(string p){ Node* cur=root; for(char c: p){ int i=c-'a'; if(!cur->nxt[i]) return false; cur=cur->nxt[i]; } return true; }
};
