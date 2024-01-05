class Solution {
private:
    bool prime[1000001]={false};
public:
    // TC: O(nloglogn)
    void sieve(){
        prime[0]=prime[1]=true;

        for(int i=2; i*i<=1000000; i++) {
            if(!prime[i]) {
                for(int j=i*i; j<=1000000; j+=i)    prime[j]=true;
            }
        }
    }

    int countPrimes(int n) {
        sieve();
        int count=0;

        for(int i=2; i<n; i++) {
            if(!prime[i]) count++;
        }

        return count;
    }
};
