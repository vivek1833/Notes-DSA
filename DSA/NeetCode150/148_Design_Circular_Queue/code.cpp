
#include <vector>
using namespace std;
class MyCircularQueue
{
    vector<int> q;
    int head, tail, cnt, cap;

public:
    MyCircularQueue(int k) : q(k), head(0), tail(0), cnt(0), cap(k) {}
    bool enQueue(int value)
    {
        if (cnt == cap)
            return false;
        q[tail] = value;
        tail = (tail + 1) % cap;
        ++cnt;
        return true;
    }
    bool deQueue()
    {
        if (cnt == 0)
            return false;
        head = (head + 1) % cap;
        --cnt;
        return true;
    }
    int Front()
    {
        if (cnt == 0)
            return -1;
        return q[head];
    }
    int Rear()
    {
        if (cnt == 0)
            return -1;
        return q[(tail - 1 + cap) % cap];
    }
    bool isEmpty() { return cnt == 0; }
    bool isFull() { return cnt == cap; }
};
