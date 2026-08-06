class Solution {
    
    static final int MOD = 1_000_000_007;
    
    public int solution(int n, int count) {
        
        long[][] dp = new long[n+1][count+1];
        
        dp[0][0] = 1; //아무것도 배치하지 않았을때 아무것도 보이지 않는 경우의 수는 아무것도 배치하지 않은 1개임
        
        for(int i=1; i<=n; i++){
            for(int j=1; j<=count; j++){
                dp[i][j] = (dp[i-1][j-1] + (2*i-2)*dp[i-1][j]) % MOD;
            }
        }
        
        
        return (int) dp[n][count];
    }
}