class Solution {
    public int solution(String[][] board, int h, int w) {
        
        int n = board.length;
        int[] dy = {1,-1,0,0};
        int[] dx = {0,0,1,-1};
        int count = 0;
        
        for(int i=0; i<4; i++){
            int ddy = h + dy[i];
            int ddx = w + dx[i];
            
            if(ddy >= 0 && ddy < n && ddx >= 0 && ddx < n ){
                if(board[h][w].equals(board[ddy][ddx])){
                    count++;
                }
            }
        }
        
        return count;
    }
}