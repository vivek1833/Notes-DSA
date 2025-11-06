
#include <vector>
#include <string>
#include <map>
#include <stack>
using namespace std;
vector<string> findItinerary(vector<vector<string>>& tickets){
    map<string, multiset<string>> g;
    for(auto &t: tickets) g[t[0]].insert(t[1]);
    vector<string> res; stack<string> st; st.push("JFK");
    while(!st.empty()){
        string u=st.top();
        if(!g[u].empty()){ auto v=*g[u].begin(); g[u].erase(g[u].begin()); st.push(v); }
        else{ res.push_back(u); st.pop(); }
    }
    reverse(res.begin(), res.end());
    return res;
}
