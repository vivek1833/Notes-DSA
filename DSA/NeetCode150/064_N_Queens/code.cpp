
#include <vector>
#include <string>
using namespace std;
vector<vector<string>> resNQ; vector<int> col, d1, d2;
void dfsNQ(int n, int r, vector<string>& board){
    if(r==n){ resNQ.push_back(board); return; }
    for(int c=0;c<n;++c){
        if(col[c]||d1[r-c+n-1]||d2[r+c]) continue;
        col[c]=d1[r-c+n-1]=d2[r+c]=1; board[r][c]='Q';
        dfsNQ(n,r+1,board);
        board[r][c]='.'; col[c]=d1[r-c+n-1]=d2[r+c]=0;
    }
}
vector<vector<string>> solveNQueens(int n){
    resNQ.clear(); col.assign(n,0); d1.assign(2*n-1,0); d2.assign(2*n-1,0);
    vector<string> board(n, string(n,'.')); dfsNQ(n,0,board); return resNQ;
}
