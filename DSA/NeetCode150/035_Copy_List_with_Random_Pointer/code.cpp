
// class Node { public: int val; Node* next; Node* random; };
Node* copyRandomList(Node* head){
    if(!head) return nullptr;
    for(Node* cur=head; cur; cur=cur->next->next){
        Node* c=new Node(cur->val);
        c->next=cur->next; cur->next=c;
    }
    for(Node* cur=head; cur; cur=cur->next->next){
        if(cur->random) cur->next->random = cur->random->next;
    }
    Node* newHead=head->next;
    for(Node* cur=head; cur; ){
        Node* c=cur->next;
        cur->next=c->next;
        c->next = c->next? c->next->next: nullptr;
        cur=cur->next;
    }
    return newHead;
}
