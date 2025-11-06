#include <stack>
#include <utility>
using namespace std;

class MinStack {
    stack<pair<int,int>> st; // {value, current_min}
public:
    MinStack() {}
    void push(int val) {
        if (st.empty()) st.push({val,val});
        else st.push({val, min(val, st.top().second)});
    }
    void pop() { st.pop(); }
    int top() { return st.top().first; }
    int getMin() { return st.top().second; }
};
