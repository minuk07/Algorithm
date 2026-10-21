import java.util.*;

class Solution {
    int MOD = 20170805;
    public int solution(int m, int n, int[][] cityMap) {
        int answer = 0;
        
        int[][][] dp = new int[m+1][n+1][2];
        
        dp[1][1][0] = 1;
        dp[1][1][1] = 1;
        
        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                if(i==1 && j==1) continue;
                
                if(cityMap[i-1][j-1] == 0){
                    dp[i][j][0] = (dp[i][j-1][0] + dp[i-1][j][1]) % MOD;
                    dp[i][j][1] = (dp[i][j-1][0] + dp[i-1][j][1]) % MOD;
                }else if(cityMap[i-1][j-1] == 2){
                    dp[i][j][0] = dp[i][j-1][0];
                    dp[i][j][1] = dp[i-1][j][1];
                }
            }
        }
        
        // for(int i=1; i<=m; i++){
        //     for(int j=1; j<=n; j++){
        //         System.out.print(dp[i][j][0] + "/" + dp[i][j][1] + " ");
        //     }
        //     System.out.println();
        // }
        
        return dp[m][n][0];
    }
}