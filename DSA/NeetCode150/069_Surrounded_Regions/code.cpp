
#include <vector>
using namespace std;
void dfsSR(vector<vector<char>>& b, int i, int j){
    if(i<0||j<0||i>= (int)b.size()||j>= (int)b[0].size()||b[i][j]!='O') return;
    b[i][j]='#';
    dfsSR(b,i+1,j); dfsSR(b,i-1,j); dfsSR(b,i,j+1); dfsSR(b,i,j-1);
}
void solve(vector<vector<char>>& board){
    int m=board.size(), n=board[0].size();
    for(int i=0;i<m;++i){ dfsSR(board,i,0); dfsSR(board,i,n-1); }
    for(int j=0;j<n;++j){ dfsSR(board,0,j); dfsSR(board,m-1,j); }
    for(int i=0;i<m;++i) for(int j=0;j<n;++j){
        if(board[i][j]=='O') board[i][j]='X';
        else if(board[i][j]=='#') board[i][j]='O';
    }
}
