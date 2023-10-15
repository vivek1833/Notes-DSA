#include <bits/stdc++.h>
using namespace std;

void flood(vector<vector<int>> &image, int i, int j, int m, int n, int currcolor, int newcolor)
{
    if (i < 0 || i >= m || j < 0 || j >= n || image[i][j] == newcolor || image[i][j] != currcolor)
        return;

    image[i][j] = newcolor;
    flood(image, i - 1, j, m, n, currcolor, newcolor);
    flood(image, i + 1, j, m, n, currcolor, newcolor);
    flood(image, i, j - 1, m, n, currcolor, newcolor);
    flood(image, i, j + 1, m, n, currcolor, newcolor);
}

vector<vector<int>> floodfill(vector<vector<int>> &image, int sr, int sc, int newcolor)
{
    int m = image.size();
    int n = image[0].size();
    int currcolor = image[sr][sc];
    flood(image, sr, sc, m, n, currcolor, newcolor);
    return image;
}