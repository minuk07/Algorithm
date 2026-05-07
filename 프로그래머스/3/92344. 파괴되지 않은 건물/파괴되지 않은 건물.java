class Solution {
    
    static int n, m;
    static int[][] temp;
    
    static void attckOrShield(int type, int r1, int c1, int r2, int c2, int degree){
        if(type == 1){
            temp[r1][c1] -= degree; temp[r1][c2+1] += degree;
            temp[r2+1][c1] += degree; temp[r2+1][c2+1] -= degree;
        }
        else{
            temp[r1][c1] += degree; temp[r1][c2+1] -= degree;
            temp[r2+1][c1] -= degree; temp[r2+1][c2+1] += degree;
        }
    }
    
    static void sumArray(){
        for(int i=0; i<n; i++){
            for(int j=1; j<m; j++){
                temp[i][j] += temp[i][j-1];
            }
        }
        
        for(int j=0; j<=m; j++){
            for(int i=1; i<=n; i++){
                temp[i][j] += temp[i-1][j];
            }
        }
    }
    
    public int solution(int[][] board, int[][] skill) {
        int answer = 0; 
        n = board.length;
        m = board[0].length;
        
        temp = new int[n+1][m+1];
        
        for(int[] s : skill){
            attckOrShield(s[0], s[1], s[2], s[3], s[4], s[5]);
        }
        
        sumArray();
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(board[i][j] + temp[i][j] > 0) answer++;
            }
        }
        
        
        return answer;
    }
}