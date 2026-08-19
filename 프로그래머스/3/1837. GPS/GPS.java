import java.util.*;

class Solution {
    
    static final int INF = 1_000_000_000;
    
    static int start, end; 
    
    static boolean[][] connected;
        
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        
        start = gps_log[0];
        end = gps_log[k-1];
        
        connected = new boolean[n+1][n+1];
        
        for(int[] edge : edge_list){
            int a = edge[0];
            int b = edge[1];
            
            connected[a][b] = true;
            connected[b][a] = true;
        }
        
        int[][] dp = new int[k][n+1];
        
        for(int i=0; i<k; i++){
            Arrays.fill(dp[i], INF);
        }
        
        dp[0][start] = 0;
        
        for(int i=1; i<k; i++){
            for(int cur=1; cur<=n; cur++){
                int answer = gps_log[i];
                
                int change = (cur == answer ? 0 : 1);
                
                for(int prev=1; prev<=n; prev++){
                    
                    if(dp[i - 1][prev] == INF) continue;
                    
                    if(prev == cur || connected[prev][cur]){
                        dp[i][cur] = Math.min(dp[i][cur], dp[i-1][prev] + change);
                    }
                }
            }
        }
        
        if(dp[k-1][end] == INF){
            return -1;
        }
        
        return dp[k-1][end];
    }
}