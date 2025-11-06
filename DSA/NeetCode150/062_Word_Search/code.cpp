
#include <vector>
#include <string>
using namespace std;
bool dfsWS(vector<vector<char>>& b, int i, int j, string& w, int k){
    if(k==(int)w.size()) return true;
    if(i<0||j<0||i>= (int)b.size()||j>= (int)b[0].size()||b[i][j]!=w[k]) return false;
    char t=b[i][j]; b[i][j]='#';
    bool ok = dfsWS(b,i+1,j,w,k+1)||dfsWS(b,i-1,j,w,k+1)||dfsWS(b,i,j+1,w,k+1)||dfsWS(b,i,j-1,w,k+1);
    b[i][j]=t; return ok;
}
bool exist(vector<vector<char>>& board, string word){
    for(int i=0;i<(int)board.size();++i) for(int j=0;j<(int)board[0].size();++j) if(dfsWS(board,i,j,word,0)) return true;
    return false;
}
