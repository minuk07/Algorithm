import java.util.*;

class Solution {
    
    static final int MIN_TEMP = -10;
    static final int MAX_TEMP = 40;
    static final int OFFSET = 10;
    static final int INF = 1000000000;
    
    static int[][] dp;
    static int[] board;
    static int temperature, t1, t2, a, b;
    static int n;
    
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int answer = INF;
        
        this.board = onboard;
        this.temperature = temperature; this.t1 = t1; this.t2 = t2; this.a = a; this.b = b;
        
        n = onboard.length;
        dp = new int[n][51];
        
        for(int i=0; i<n; i++){
            Arrays.fill(dp[i], INF);
        }
        
        dp[0][temperature + OFFSET] = 0;
        
        for(int i=0; i<n-1; i++){
            for(int j=MIN_TEMP; j<=MAX_TEMP; j++){
                
                int idx = j + OFFSET;
                
                if(dp[i][idx] == INF) continue;
                
                int next = j;
                
                if(j < temperature){
                    next++;
                }else if(j > temperature){
                    next--;
                }
                
                //에어컨 꺼져있는 상황
                update(i+1, next, dp[i][idx]);
                
                //에어컨 켠 상황
                update(i+1, j, dp[i][idx] + b);//온도유지
                
                update(i+1, j+1, dp[i][idx] + a);//온도올림
                update(i+1, j-1, dp[i][idx] + a);//온도내림
            }
        }
        
        for(int i=MIN_TEMP; i<=MAX_TEMP; i++){
            if(board[n-1] == 1 && (i<t1 || i>t2)) continue;
            
            answer = Math.min(answer, dp[n-1][i+OFFSET]);
        }
        
        return answer;
    }
    
    static void update(int time, int temp, int cost){
        if(temp < MIN_TEMP || temp > MAX_TEMP) return;
        
        if(board[time] == 1){
            if(temp < t1 || temp > t2) return;
        }
        
        int idx = temp + OFFSET;
        dp[time][idx] = Math.min(dp[time][idx], cost);
    }
}