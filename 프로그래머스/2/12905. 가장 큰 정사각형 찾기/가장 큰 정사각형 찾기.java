class Solution
{
    public int solution(int [][]board)
    {
        int answer = 0;
        
        int n = board.length;
        int m = board[0].length;
        
        int[][] dp = new int[n][m];
        
        for(int i=0; i<n; i++){
            if(board[i][0] == 1){
                answer = 1;
                dp[i][0] = 1;
            }
        }
        for(int j=0; j<m; j++){
            if(board[0][j] == 1){
                answer = 1;
                dp[0][j] = 1;
            }
        }
        
        for(int i=1; i<n; i++){
            for(int j=1; j<m; j++){
                if(board[i][j] == 1){
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                    answer = Math.max(answer, dp[i][j]); 
                }
                
            }
        }
        
        return answer * answer;
    }
}