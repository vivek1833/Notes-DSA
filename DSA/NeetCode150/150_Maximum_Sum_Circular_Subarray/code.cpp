
#include <vector>
using namespace std;
int maxSubarraySumCircular(vector<int> &A)
{
    int total = 0, maxSum = A[0], curMax = 0, minSum = A[0], curMin = 0;
    for (int x : A)
    {
        curMax = max(x, curMax + x);
        maxSum = max(maxSum, curMax);
        curMin = min(x, curMin + x);
        minSum = min(minSum, curMin);
        total += x;
    }
    if (maxSum > 0)
        return max(maxSum, total - minSum);
    return maxSum;
}
