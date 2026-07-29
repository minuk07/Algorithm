import java.util.*;

class Solution {
    
    static final int INF = 100_000_000;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        
        int[][] dist = new int[n+1][n+1];
    
        for(int i=1; i<=n; i++){
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
        
        for(int[] f : fares){
            int node1 = f[0];
            int node2 = f[1];
            int cost = f[2];
            
            dist[node1][node2] = cost;
            dist[node2][node1] = cost;
        }
        
        for(int k=1; k<=n; k++){
            for(int i=1; i<=n; i++){
                for(int j=1; j<=n; j++){
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        
        int[] share = new int[n+1];
        
        for(int i=1; i<=n; i++){
            share[i] = dist[s][i] + dist[i][a] + dist[i][b];
        }
        
        for(int i=1; i<=n; i++){
            answer = Math.min(answer, share[i]);
        }
        
        
        return answer;
    }
}