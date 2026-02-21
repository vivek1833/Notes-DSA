class Solution {	
    int[][][] dp;

    private int solve(int n, int m, int k, String A, String B, String C) {
        if(n==0 || m==0 || k==0)    return 0;
        if(dp[n][m][k] != -1)   return dp[n][m][k];

        if(A.charAt(n-1) == B.charAt(m-1) && B.charAt(m-1) == C.charAt(k-1))
            return dp[n][m][k] = 1 + solve(n-1, m-1, k-1, A, B, C);
            
        return dp[n][m][k] = Math.max(
            Math.max(solve(n-1, m, k, A, B, C), solve(n, m-1, k, A, B, C)),
            solve(n, m, k-1, A, B, C)
        );
    }

	public int LCS(String A, String B, String C) {
        int n = A.length(), m = B.length(), k = C.length();

        this.dp = new int[n+1][m+1][k+1];

        for(int i=0; i<n+1; i++) {
            for(int j=0; j<m+1; j++) {
                for(int l=0; l<k+1; l++) {
                    dp[i][j][l] = -1;
                }
            }
        }

        return solve(n, m, k, A, B, C);
	}
}
