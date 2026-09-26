import java.util.*;

class Solution {
    
    static final int INF = 1000;
    
    public int solution(int[][] info, int n, int m) {
        int answer = INF;
        int len = info.length;
        
        
        int[][] dp = new int[len+1][m];
        
        for(int i=0; i<=len; i++){
            Arrays.fill(dp[i], INF);
        }
        
        dp[0][0] = 0;
        
        for(int i=1; i<=len; i++){
            int a = info[i-1][0];
            int b = info[i-1][1];
            
            for(int j=0; j<m; j++){
                dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + a);
                
                if(j+b < m){
                    dp[i][j+b] = Math.min(dp[i][j+b], dp[i-1][j]);
                }
            }
        }
        
        for(int j=0; j<m; j++){
            answer = Math.min(dp[len][j], answer);
        }
        
        if(answer >= n) return -1;
        
        return answer;
    }
}