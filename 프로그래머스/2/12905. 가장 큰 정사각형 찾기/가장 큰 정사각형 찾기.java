import java.util.*;

class Solution
{
    public int solution(int [][]board)
    {
        int answer = 0;
        int result = 0;
        int n = board.length;
        int m = board[0].length;
        int[][] dp = new int[n][m];
        
        for(int i=0; i<n; i++){
            if(board[i][0] == 1) dp[i][0] = 1;
            else dp[i][0] = 0;
            
            result = Math.max(result, dp[i][0]);
        }
        
        for(int i=0; i<m; i++){
            if(board[0][i] == 1) dp[0][i] = 1;
            else dp[0][i] = 0;
            
            result = Math.max(result, dp[0][i]);
        }
        
        for(int i=1; i<n; i++){
            for(int j=1; j<m; j++){
                if(board[i][j] == 0) dp[i][j] = 0;
                else{
                    dp[i][j] = Math.min(dp[i-1][j-1],dp[i-1][j]);
                    dp[i][j] = Math.min(dp[i][j], dp[i][j-1]) + 1;
                    result = Math.max(result, dp[i][j]);
                }
            }
        }
        
        return result * result;
    }
}