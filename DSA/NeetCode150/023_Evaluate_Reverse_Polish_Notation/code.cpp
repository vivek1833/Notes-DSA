
#include <vector>
#include <string>
#include <stack>
using namespace std;
int evalRPN(vector<string>& tokens){
    stack<long long> st;
    for(auto &s: tokens){
        if(s=="+"||s=="-"||s=="*"||s=="/"){
            long long b=st.top(); st.pop();
            long long a=st.top(); st.pop();
            if(s=="+") st.push(a+b);
            else if(s=="-") st.push(a-b);
            else if(s=="*") st.push(a*b);
            else st.push(a/b);
        }else st.push(stoll(s));
    }
    return (int)st.top();
}
