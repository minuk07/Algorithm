import java.util.*;

class Solution {
    
    int[][] dp;
    
    public int[] solution(int target) {
        
        dp = new int[target+1][2];
        
        for(int i=1; i<=target; i++){
            dp[i][0] = Integer.MAX_VALUE;
        }
        
        for(int i=1; i<=target; i++){
            
            if(i - 50 >= 0){
                shoot(i, i-50, 1);
            }
            
            for(int j=1; j<=20; j++){
                if(i-j >= 0){
                    shoot(i, i-j, 1);
                }
                if(i- (j*2) >= 0){
                    shoot(i, i-(j*2), 0);
                }
                if(i-(j*3) >=0){
                    shoot(i, i-(j*3), 0);
                }
            }
        }
        
        return new int[]{dp[target][0], dp[target][1]};
    }
    
    private void shoot(int cur, int prev, int isBullOrSingle){
        if(dp[cur][0] > dp[prev][0] + 1){
            dp[cur][0] = dp[prev][0] + 1;
            dp[cur][1] = dp[prev][1] + isBullOrSingle;
        }else if(dp[cur][0] == dp[prev][0] + isBullOrSingle && isBullOrSingle == 1){
            dp[cur][1] = Math.max(dp[cur][1], dp[prev][1] + isBullOrSingle);
        }
    }
}