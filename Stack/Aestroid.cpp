#include <bits/stdc++.h>
using namespace std;

vector<int> collision(vector<int> &asteroid)
{
    vector<int> res;
    for (int i = 0; i < asteroid.size(); i++)
    {
        if (res.empty() || asteroid[i] > 0)
        {
            res.push_back(asteroid[i]);
        }
        else
        {
            while (!res.empty() && res.back() > 0 && res.back() < asteroid[i])
            {
                res.pop_back();
            }
            if (!res.empty() && res.back() + asteroid[i] == 0)
            {
                res.pop_back();
            }
            else if (res.empty() || res.back() < 0)
            {
                res.push_back(asteroid[i]);
            }
        }
    }
    return res;
}