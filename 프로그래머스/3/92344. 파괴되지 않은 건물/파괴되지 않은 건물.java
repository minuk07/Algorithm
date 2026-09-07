class Solution {
    
    static int[][] sum;
    
    static void destroy(int type, int r1, int c1, int r2, int c2, int degree){
        if(type == 1){ //공격
            sum[r1][c1] -= degree;
            sum[r1][c2+1] += degree;
            sum[r2+1][c1] += degree;
            sum[r2+1][c2+1] -= degree;
        }else{
            sum[r1][c1] += degree;
            sum[r1][c2+1] -= degree;
            sum[r2+1][c1] -= degree;
            sum[r2+1][c2+1] += degree;
        }
    }
    
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        
        int n = board.length;
        int m = board[0].length;
        
        sum = new int[n+1][m+1];
        
        for(int[] s : skill){
            destroy(s[0], s[1], s[2], s[3], s[4], s[5]);
        }
        
        for (int i = 0; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                sum[i][j] += sum[i][j - 1];
            }
        }

        for (int j = 0; j <= m; j++) {
            for (int i = 1; i <= n; i++) {
                sum[i][j] += sum[i - 1][j];
            }
        }
            
        // for(int i=0; i<=n; i++){
        //     for(int j=0; j<=m; j++){
        //         System.out.print(result[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(board[i][j] + sum[i][j] > 0) answer++;
            }
        }
        
        return answer;
    }
}