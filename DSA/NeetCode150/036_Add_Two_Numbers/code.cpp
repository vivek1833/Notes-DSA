
// struct ListNode { int val; ListNode* next; };
ListNode* addTwoNumbers(ListNode* l1, ListNode* l2){
    ListNode dummy(0); ListNode* cur=&dummy; int carry=0;
    while(l1||l2||carry){
        int s=carry + (l1?l1->val:0) + (l2?l2->val:0);
        carry=s/10; cur->next=new ListNode(s%10); cur=cur->next;
        if(l1) l1=l1->next; if(l2) l2=l2->next;
    }
    return dummy.next;
}
