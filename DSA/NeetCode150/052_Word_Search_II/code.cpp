
#include <vector>
#include <string>
using namespace std;
struct TN{ bool end=false; string word=""; TN* nxt[26]={0}; };
void add(TN* r, const string &w){ TN* cur=r; for(char c:w){ int i=c-'a'; if(!cur->nxt[i]) cur->nxt[i]=new TN(); cur=cur->nxt[i]; } cur->end=true; cur->word=w; }
void dfs(vector<vector<char>>& b, int i, int j, TN* n, vector<string>& out){
    char c=b[i][j]; int idx=c-'a'; TN* nxt=n->nxt[idx]; if(!nxt) return;
    if(nxt->end){ out.push_back(nxt->word); nxt->end=false; }
    b[i][j] = '#';
    int m=b.size(), k=b[0].size();
    if(i>0 && b[i-1][j]!='#') dfs(b,i-1,j,nxt,out);
    if(j>0 && b[i][j-1]!='#') dfs(b,i,j-1,nxt,out);
    if(i+1<m && b[i+1][j]!='#') dfs(b,i+1,j,nxt,out);
    if(j+1<k && b[i][j+1]!='#') dfs(b,i,j+1,nxt,out);
    b[i][j] = c;
}
vector<string> findWords(vector<vector<char>>& board, vector<string>& words){
    TN* root=new TN();
    for(auto &w: words) add(root,w);
    vector<string> out;
    int m=board.size(), n=board[0].size();
    for(int i=0;i<m;++i) for(int j=0;j<n;++j) dfs(board,i,j,root,out);
    return out;
}
