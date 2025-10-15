#include <bits/stdc++.h>
using namespace std;

struct comp {
    bool operator()(const pair<int,int> &a, pair<int,int> &b) const {
        if(a.first == b.first)
            return a.second > b.second;
        return a.first < b.first;
    }
};

vector<int> solve(vector<int> &vec, int k)
{
    int n=vec.size();
    
    // number, frequency
    unordered_map<int,int> mp;          
    
    // -frequency, number
    priority_queue<pair<int,int>, vector<pair<int,int>>, comp> pq;  
    vector<int> answer;
    
    if(k > n)   return answer;
    
    for(int i=0; i<n; i++) {
        mp[vec[i]]++;
    }
    
    for(auto it: mp) {
        int freq=it.second;
        int number=it.first;
        
        pq.push({freq, number});
    }
    
    while(k--) {
        auto it=pq.top();
        pq.pop();
        
        int num=it.second;
        answer.push_back(num);
    }
    
    return answer;
}

int main() 
{
    vector<int> vec = {-1,-1,2,2,3,3};
    int k=7;
    
    vector<int> answer = solve(vec, k);
    
    for(int it: answer) {
        cout << it << " ";
    }
    
    return 0;
}

bool isBracket(char it)
{
    return (it=='(' || it=='[' || it=='{' || it=='<' || it=='|'
    || it==')' || it==']' || it=='}' || it=='>');
}

bool solve(string &str)
{
    stack<char> st;
    
    for(char it: str) {
        if(!isBracket(it))  continue;
        
        auto it=bra
        if(
            !st.empty() 
            && ((it==')' && st.top()=='(')
            || (it==']' && st.top()=='[')
            || (it=='}' && st.top()=='{')
            || (it=='>' && st.top()=='<')
            || (it=='|' && st.top()=='|'))
        ) {
            st.pop();
        }
        else if(isBracket(it)) {
            st.push(it);
        }
    }
    
    return (st.empty() ? true : false);
}

int main() 
{
    vector<string> tests = {
        "()",          // true
        "(]",          // false
        "([{}])",      // true
        "((())",       // false
        "||",          // true
        "|||",         // false
        "|()|",        // true
        "||()|",       // false
        "|(|)|",       // true
        "|a+b| - (c*d)", // true
        "|a+b - (c*d)", // false
        "<<>>",        // true
        "<{[()]}>",    // true
        "<{[(])}>",    // false
    };
    
    for(auto it: tests) {
        cout << it << " " <<  (bool)solve(it) << endl;
    }
    
    return 0;
}