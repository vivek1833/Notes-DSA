
// struct ListNode { int val; ListNode* next; };
void reorderList(ListNode* head){
    if(!head||!head->next) return;
    // find mid
    ListNode* slow=head; ListNode* fast=head;
    while(fast&&fast->next){ slow=slow->next; fast=fast->next->next; }
    // reverse second half
    ListNode* prev=nullptr; ListNode* cur=slow->next; slow->next=nullptr;
    while(cur){ ListNode* nxt=cur->next; cur->next=prev; prev=cur; cur=nxt; }
    // merge
    ListNode* a=head; ListNode* b=prev;
    while(b){
        ListNode* an=a->next, *bn=b->next;
        a->next=b; b->next=an;
        a=an; b=bn;
    }
}
