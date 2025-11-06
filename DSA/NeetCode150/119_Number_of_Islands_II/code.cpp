
#include <vector>
using namespace std;
struct UF{ vector<int> p; UF(int n):p(n,-1){}; int find(int x){ return p[x]==x?x:p[x]=find(p[x]); } void unite(int a,int b){ a=find(a); b=find(b); if(a!=b) p[a]=b; } };
vector<int> numIslands2(int m, int n, vector<vector<int>>& ops){
    vector<int> res; UF uf(m*n); vector<int> present(m*n,0); int cnt=0;
    int dirs[4][2]={{1,0},{-1,0},{0,1},{0,-1}};
    for(auto &op: ops){
        int r=op[0], c=op[1], id=r*n+c;
        present[id]=1; uf.p[id]=id; cnt++;
        for(auto &d: dirs){
            int nr=r+d[0], nc=c+d[1], nid;
            if(nr>=0&&nc>=0&&nr<m&&nc<n){ nid=nr*n+nc; if(present[nid]){ int pa=uf.find(id), pb=uf.find(nid); if(pa!=pb){ uf.unite(pa,pb); cnt--; } } }
        }
        res.push_back(cnt);
    }
    return res;
}
