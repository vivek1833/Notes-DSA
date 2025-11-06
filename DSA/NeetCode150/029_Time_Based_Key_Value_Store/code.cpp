
#include <string>
#include <unordered_map>
#include <vector>
using namespace std;
class TimeMap {
    unordered_map<string, vector<pair<int,string>>> mp;
public:
    TimeMap(){}
    void set(string key, string value, int timestamp){
        mp[key].push_back({timestamp, value});
    }
    string get(string key, int timestamp){
        auto &v=mp[key];
        int l=0, r=(int)v.size()-1, idx=-1;
        while(l<=r){
            int m=l+(r-l)/2;
            if(v[m].first<=timestamp){ idx=m; l=m+1; } else r=m-1;
        }
        return idx==-1? "" : v[idx].second;
    }
};
