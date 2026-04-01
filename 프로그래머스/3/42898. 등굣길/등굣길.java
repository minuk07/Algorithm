import java.util.*;

class Solution {
    
    static int[][] map;
    
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        
        map = new int[n][m];
        
        for(int i=0; i<puddles.length; i++){
            int wy = puddles[i][1] - 1; int wx = puddles[i][0] - 1;
            
            map[wy][wx] = -1;
        }
        
        int[][] dp = new int[n][m];
        
        if(map[0][0] == -1) return 0;
        
        dp[0][0] = 1;
        
        for(int i=1; i<n; i++){
            if(map[i][0] == -1) break;
            dp[i][0] = 1;
        }
        
        for(int i=1; i<m; i++){
            if(map[0][i] == -1) break;
            dp[0][i] = 1;
        }
        
        for(int i=1; i<n; i++){
            for(int j=1; j<m; j++){
                if(map[i][j] == -1) {
                    dp[i][j] = 0;
                    continue;
                }
                
                dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 1000000007;
                
            }
        }
        
        // for(int i=0; i<n; i++){
        //     for(int j=0; j<m; j++){
        //         System.out.print(dp[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        
        return dp[n-1][m-1];
    }
}