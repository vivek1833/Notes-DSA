
#include <vector>
#include <stack>
using namespace std;
int largestRectangleArea(vector<int>& h){
    h.push_back(0);
    stack<int> st;
    int ans=0;
    for(int i=0;i<(int)h.size();++i){
        while(!st.empty() && h[st.top()] > h[i]){
            int height=h[st.top()]; st.pop();
            int left = st.empty()? -1 : st.top();
            ans = max(ans, height * (i - left - 1));
        }
        st.push(i);
    }
    return ans;
}
