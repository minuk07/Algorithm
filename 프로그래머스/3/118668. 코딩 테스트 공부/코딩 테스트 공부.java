import java.util.*;

class Solution {
    
    static final int INF = Integer.MAX_VALUE;
    
    public int solution(int alp, int cop, int[][] problems) {
        
        int max_alp = alp;
        int max_cop = cop;
        
        for(int[] problem : problems){
            max_alp = Math.max(max_alp, problem[0]);
            max_cop = Math.max(max_cop, problem[1]);
        }
        
        
        int[][] dp = new int[max_alp+1][max_cop+1];
        
        for(int i=0; i<=max_alp; i++){
            Arrays.fill(dp[i], INF);
        }
            
        dp[alp][cop] = 0;
        
        for(int i=alp; i<=max_alp; i++){
            for(int j=cop; j<=max_cop; j++){
                
                if(i+1 <= max_alp){
                    dp[i+1][j] = Math.min(dp[i][j] + 1, dp[i+1][j]);
                }
                
                if(j+1 <= max_cop){
                    dp[i][j+1] = Math.min(dp[i][j] + 1, dp[i][j+1]);
                }
                
                for(int[] problem : problems){
                    int req_alp = problem[0];
                    int req_cop = problem[1];
                    
                    int rwd_alp = problem[2];
                    int rwd_cop = problem[3];
                    
                    int cost = problem[4];
                    
                    if(i >= req_alp && j >=req_cop){
                        
                        int target_alp = Math.min(max_alp, i+rwd_alp);
                        int target_cop = Math.min(max_cop, j+rwd_cop);
                        
                        dp[target_alp][target_cop] = Math.min(dp[target_alp][target_cop], dp[i][j] + cost);
                        
                    }
                }
            }
        }
        
        return dp[max_alp][max_cop];
    }
}