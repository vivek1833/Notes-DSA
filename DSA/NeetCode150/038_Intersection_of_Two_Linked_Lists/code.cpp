
// struct ListNode { int val; ListNode* next; };
ListNode *getIntersectionNode(ListNode *a, ListNode *b){
    ListNode* p=a; ListNode* q=b;
    while(p!=q){ p = p? p->next : b; q = q? q->next : a; }
    return p;
}
