
// struct ListNode { int val; ListNode* next; };
bool hasCycle(ListNode *head){
    if(!head) return false;
    ListNode* s=head; ListNode* f=head->next;
    while(f && f->next){
        if(s==f) return true;
        s=s->next; f=f->next->next;
    }
    return false;
}
