#include <bits/stdc++.h>
using namespace std;

int findArea(vector<vector<char>> &grid, int currrow, int currcol, int row, int col)
{
    if (currrow < 0 || currrow >= row || currcol < 0 || currcol >= col || grid[currrow][currcol] == 0)
        return;

    grid[currrow][currcol] = 0;

    int count = 1;
    count += findArea(grid, currrow - 1, currcol, row, col);
    count += findArea(grid, currrow + 1, currcol, row, col);
    count += findArea(grid, currrow, currcol - 1, row, col);
    count += findArea(grid, currrow, currcol + 1, row, col);

    return count;
}

int numisland(vector<vector<char>> &grid)
{
    int ans = 0;
    int row = grid.size();
    int col = grid[0].size();

    for (int currrow = 0; currrow < row; currrow++)
    {
        for (int currcol = 0; currcol < col; currcol++)
        {
            if (grid[currrow][currcol] == '1')
            {
                ans = max(ans, findArea(grid, currrow, currcol, row, col));
            }
        }
    }
    return ans;
}