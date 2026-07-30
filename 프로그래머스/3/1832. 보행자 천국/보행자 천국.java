import java.util.*;

class Solution {
    
    static final int MOD = 20170805;
   
    public int solution(int m, int n, int[][] cityMap) {
        
        int[][][] dp = new int[m+1][n+1][2];
        dp[1][1][0] = 1; dp[1][1][1] = 1;
        
        //0 : 오른쪽, 1 : 아래
        
        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                
                if(i==1 && j==1) continue;
                
                int cur = cityMap[i-1][j-1];
                
                if(cur == 0){
                    dp[i][j][0] = (dp[i-1][j][1] + dp[i][j-1][0]) % MOD;
                    dp[i][j][1] = (dp[i-1][j][1] + dp[i][j-1][0]) % MOD;
                }else if(cur == 1){
                    dp[i][j][0] = 0; dp[i][j][1] = 0;
                }else{
                    dp[i][j][0] = dp[i][j-1][0];
                    dp[i][j][1] = dp[i-1][j][1];
                }
            }
        }
        
        
        return dp[m][n][0];
    }
}