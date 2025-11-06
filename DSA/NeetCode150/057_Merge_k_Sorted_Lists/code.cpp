
#include <vector>
#include <queue>
using namespace std;
// struct ListNode { int val; ListNode* next; };
struct Cmp{ bool operator()(ListNode* a, ListNode* b) const { return a->val > b->val; } };
ListNode* mergeKLists(vector<ListNode*>& lists){
    priority_queue<ListNode*, vector<ListNode*>, Cmp> pq;
    for(auto n: lists) if(n) pq.push(n);
    ListNode dummy(0), *cur=&dummy;
    while(!pq.empty()){
        auto n=pq.top(); pq.pop();
        cur->next=n; cur=cur->next;
        if(n->next) pq.push(n->next);
    }
    return dummy.next;
}
