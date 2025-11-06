
#include <vector>
#include <unordered_map>
#include <unordered_set>
#include <queue>
using namespace std;
class Twitter{
    int time=0;
    unordered_map<int, unordered_set<int>> fol;
    unordered_map<int, vector<pair<int,int>>> tw; // user -> {time,id}
public:
    Twitter(){}
    void postTweet(int userId, int tweetId){ tw[userId].push_back({++time, tweetId}); }
    vector<int> getNewsFeed(int userId){
        struct Node{int time, uid, idx;};
        struct Cmp{ bool operator()(const Node& a,const Node& b) const { return a.time < b.time; } };
        priority_queue<Node, vector<Node>, Cmp> pq;
        fol[userId].insert(userId);
        for(int f: fol[userId]){
            auto &v=tw[f];
            if(!v.empty()) pq.push({v.back().first, f, (int)v.size()-1});
        }
        vector<int> res;
        while(!pq.empty() && (int)res.size()<10){
            auto cur=pq.top(); pq.pop();
            res.push_back(tw[cur.uid][cur.idx].second);
            if(cur.idx>0) pq.push({tw[cur.uid][cur.idx-1].first, cur.uid, cur.idx-1});
        }
        return res;
    }
    void follow(int followerId, int followeeId){ fol[followerId].insert(followeeId); }
    void unfollow(int followerId, int followeeId){ if(followeeId!=followerId) fol[followerId].erase(followeeId); }
};
