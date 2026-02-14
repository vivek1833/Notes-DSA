/*
    Problem Link: https://leetcode.com/problems/max-area-of-island/
    
    Max Area of Island
*/

class Max_AreaIsland {
    private int dfs(int x, int y, int[] rows, int[] cols, boolean[][] vis, int n, int m, int[][] grid)
        {
            vis[x][y]=true;
            int area=0;
    
            for(int i=0; i<4; i++) {
                int nx=x+rows[i];
                int ny=y+cols[i];
    
                if(nx>=0 && ny>=0 && nx<n && ny<m && !vis[nx][ny] && grid[nx][ny]==1)
                    area += dfs(nx,ny,rows,cols,vis,n,m,grid);
            }
            return 1+area;
        }
    
    public int maxAreaOfIsland(int[][] grid)
        {
            int n=grid.length, m=grid[0].length, cnt, mx=Integer.MIN_VALUE;
            boolean[][] vis = new boolean[n][m];
            int[] dx = new int[]{-1,0,1,0};
            int[] dy = new int[]{0,-1,0,1};
    
            for(int i=0; i<n; i++) {
                for(int j=0; j<m; j++) {
                    if(!vis[i][j] && grid[i][j]==1) {
                        cnt=dfs(i,j,dx,dy,vis,n,m,grid);
                        mx=Math.max(cnt,mx);
                    }
                }
            }
    
            return (mx==Integer.MIN_VALUE ? 0 : mx);
        }
    };