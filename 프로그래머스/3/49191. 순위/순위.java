import java.util.*;

class Solution {
    
    static final int INF = 100000;
    
    public int solution(int n, int[][] results) {
        int answer = 0;
        
        int[][] rank = new int[n+1][n+1];
        
        for(int i=1; i<=n; i++){
            Arrays.fill(rank[i], 0);
        }
        
        for(int[] result : results){
            int win = result[0];
            int lose = result[1];
            
            rank[win][lose] = 1;
            rank[lose][win] = -1;
        }
        
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                for(int k=1; k<=n; k++){
                    if(rank[i][k] == 1 && rank[k][j] == 1){ //i가 k를 이겼고, k가 j를 이겼다면 i는 j를 이김
                        rank[i][j] = 1;
                        rank[j][i] = -1;
                    }
                    if(rank[i][k] == -1 && rank[k][j] == -1){ //i가 k한테 졌고, k는 j한테 졌다면 i는 j한테 짐
                        rank[i][j] = -1;
                        rank[j][i] = 1;
                    }
                }
            }
        }
        
        for(int i=1; i<=n; i++){
            int cnt = 0;
            for(int j=1; j<=n; j++){
                if(rank[i][j] != 0) cnt++;
            }
            if(cnt == n-1) answer++;
        }
        
        
        
        return answer;
    }
}