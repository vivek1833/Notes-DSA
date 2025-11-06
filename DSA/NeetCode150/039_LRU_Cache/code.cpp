
#include <unordered_map>
#include <list>
using namespace std;
class LRUCache {
    int cap;
    list<pair<int,int>> dq; // {key,val}
    unordered_map<int, list<pair<int,int>>::iterator> mp;
public:
    LRUCache(int capacity):cap(capacity){}
    int get(int key){
        auto it=mp.find(key);
        if(it==mp.end()) return -1;
        dq.splice(dq.begin(), dq, it->second);
        return it->second->second;
        }
    void put(int key, int value){
        auto it=mp.find(key);
        if(it!=mp.end()){
            it->second->second=value;
            dq.splice(dq.begin(), dq, it->second);
            return;
        }
        if((int)dq.size()==cap){
            auto last=dq.back(); dq.pop_back();
            mp.erase(last.first);
        }
        dq.push_front({key,value});
        mp[key]=dq.begin();
    }
};
