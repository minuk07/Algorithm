import java.util.*;

class Solution {
    
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};
    
    static int n,m;
    
    static class Result{
        boolean isWin; int turn;
        Result(boolean isWin, int turn){
            this.isWin=isWin; this.turn=turn;
        }
    }
    
    static boolean inRange(int y, int x){
        return (y>=0 && x>=0 && y<n && x<m);
    }
    
    static Result dfs(int meY, int meX, int opY, int opX, int[][] board){
        
        if(board[meY][meX] == 0){
            return new Result(false, 0);
        }
        
        int winTurn = 100000;
        int loseTurn = 0;
        
        boolean canWin = false;
        boolean canMove = false;
        
        for(int i=0; i<4; i++){
            int ddy = meY + dy[i];
            int ddx = meX + dx[i];
            
            if(!inRange(ddy, ddx)) continue;
            if(board[ddy][ddx] == 0) continue;
            
            board[meY][meX] = 0;
            
            canMove = true;
            Result result = dfs(opY, opX, ddy, ddx, board);
            
            if(!result.isWin){ //상대방 패배
                winTurn = Math.min(winTurn, result.turn);
                canWin = true;
            }else{
                loseTurn = Math.max(loseTurn, result.turn);
            }
        }
        
        board[meY][meX] = 1;
        
        if(!canMove){
            return new Result(false, 0);
        }else{
            if(!canWin){
                return new Result(false, loseTurn + 1);
            }else{
                return new Result(true, winTurn + 1);
            }
        }
        
    }
    
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        
        n = board.length;
        m = board[0].length;
        
        Result answer = dfs(aloc[0], aloc[1], bloc[0], bloc[1], board);
        
        return answer.turn;
    }
    
    
}