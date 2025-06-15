// https://www.geeksforgeeks.org/problems/euler-circuit-and-path/1

class Solution {
    // If no. of odd degree nodes = 0  → Euler circuit
    // If no. of odd degree nodes = 2  → Euler path
    // In all other cases neither Euler circuit nor Euler path
public:
	int isEulerCircuit(int V, vector<int>adj[])
	{
	    int odd=0;
	   
	    for(int i=0; i<V; i++){
	        if(adj[i].size()%2 != 0)   odd++;
	    }
	   
	    if(odd==0)    return 2;
	    else if(odd==2)	    return 1;
	    else	    return 0;
	}
};