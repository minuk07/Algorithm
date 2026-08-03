import java.util.*;

class Solution {
    
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    
    static int n,m;
    static int[][] maze;
    
    static boolean turn;
    
    static int ry, rx, by, bx;
    
    static boolean[][] visitedRed, visitedBlue;
    
    static boolean inRange(int y, int x){
        return (y>=0 && x>=0 && y<n && x<m);
    }
    
    public int solution(int[][] maze) {
        int answer = 0;
        
        n = maze.length;
        m = maze[0].length;
        
        this.maze = maze;
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(maze[i][j] == 1){
                    ry = i; rx = j;
                }else if(maze[i][j] == 2){
                    by= i; bx = j;
                }
            }
        }
        
        visitedRed = new boolean[n][m]; visitedBlue = new boolean[n][m];
        
        visitedRed[ry][rx] = true; visitedBlue[by][bx] = true;
        
        answer = dfs(ry, rx, by, bx, 0);
        
        return (answer != Integer.MAX_VALUE) ? answer : 0;
    }
    
    static int dfs(int ry, int rx, int by, int bx, int cnt){
        
        int ret = Integer.MAX_VALUE;
        
        if(maze[ry][rx] == 3 && maze[by][bx] == 4) return cnt;
        
        for(int i=0; i<4; i++){
            
            int nry = ry; int nrx = rx;
            
            if(maze[nry][nrx] != 3){
                nry += dy[i]; nrx += dx[i];
            }
            
            for(int j=0; j<4; j++){
                
                int nby = by; int nbx = bx;
                
                if(maze[nby][nbx] != 4){
                    nby += dy[j]; nbx += dx[j];
                }
                
                if(canMove(ry, rx, nry, nrx, by, bx, nby, nbx)){
                    visitedRed[nry][nrx] = true;
                    visitedBlue[nby][nbx] = true;
                    
                    ret = Math.min(dfs(nry, nrx, nby, nbx, cnt+1), ret);
                    
                    if(!(ry == nry && rx == nrx)) visitedRed[nry][nrx] = false;
                    if(!(by == nby && bx == nbx)) visitedBlue[nby][nbx] = false;
                }
            }
        }
        
        return ret;
    }
    
    static boolean canMove(int ry, int rx, int nry, int nrx, int by, int bx, int nby, int nbx){
        
        if(!inRange(nry, nrx) || !inRange(nby, nbx)) return false;
        
        if(!(ry == nry && rx == nrx) && visitedRed[nry][nrx]) return false;
        if(!(by == nby && bx == nbx) && visitedBlue[nby][nbx]) return false;
        
        if(maze[nry][nrx] == 5 || maze[nby][nbx] == 5) return false;
        
        if(nry == nby && nrx == nbx) return false;
        
        if((nry == by && nrx == bx) && (nby == ry && nbx == rx)) return false;
        
        return true;
    }
}