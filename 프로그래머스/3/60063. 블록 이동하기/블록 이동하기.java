import java.util.*;

class Solution {
    //dir = 0 : 가로 , dir = 1: 세로
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static int[] rotate = {-1, 1};
    
    static int n;
    static boolean[][][] visited;
    
    static class Robot{
        int y, x, dir, time;
        
        Robot(int y, int x, int dir, int time){
            this.y=y; this.x=x; this.dir=dir; this.time=time;    
        }
    }
    
    static boolean inRange(int y, int x){
        return (y>=0 && x>=0 && y<n && x<n);
    }
    
    static int[][] getPos(Robot r){
        if(r.dir == 0){
            return new int[][]{{r.y,r.x},{r.y,r.x+1}};
        }
        else{
            return new int[][]{{r.y,r.x},{r.y+1,r.x}};
        }
    }
    
    static boolean canGo(Robot r, int[][] board){
        int[][] pos = getPos(r);
        
        for(int[] p : pos){
            int y = p[0]; int x = p[1];
            
            if(!inRange(y, x)) return false;
            if(board[y][x] == 1) return false;
        }
        
        return true;
    }
    
    static boolean isArrived(Robot r){
        
        int[][] pos = getPos(r);
        
        for(int[] p : pos){
            if(p[0]==n-1 && p[1] == n-1) return true;
        }
        
        return false;
    }
    
    static int bfs(int[][] board){
        Queue<Robot> q = new LinkedList<>();
        visited[0][0][0] = true;
        q.add(new Robot(0, 0, 0, 0));
        
        while(!q.isEmpty()){
            Robot cur = q.poll();
            
            if(isArrived(cur)) return cur.time;
            
            int sy = cur.y; int sx = cur.x; int dir = cur.dir; int time = cur.time;
            
            for(int i=0; i<4; i++){
                int ddy = sy + dy[i];
                int ddx = sx + dx[i];
                
                Robot ddr = new Robot(ddy, ddx, dir, time + 1);
                
                if(!canGo(ddr, board) || visited[ddy][ddx][dir]) continue;
                
                visited[ddy][ddx][dir] = true;
                q.add(ddr);
            }
            
            if(dir == 0){
                for(int r : rotate){
                    int ddy = sy + r;
                    
                    if(inRange(ddy, sx) && inRange(ddy, sx+1) 
                       && board[ddy][sx] == 0 && board[ddy][sx+1] == 0){
                        
                        int ry = (r==1 ? sy : sy -1);
                        
                        if(!visited[ry][sx][1]){
                            q.add(new Robot(ry, sx, 1, time+1));
                        }
                        if(!visited[ry][sx+1][1]){
                            q.add(new Robot(ry, sx+1, 1, time+1));
                        }
                    }
                }
            }else{
                for(int r : rotate){
                    int ddx = sx + r;
                    
                    if(inRange(sy, ddx) && inRange(sy+1, ddx)
                      && board[sy][ddx] == 0 && board[sy+1][ddx] == 0){
                        
                        int rx = (r==1 ? sx : sx-1);
                        
                        if(!visited[sy][rx][0]){
                            q.add(new Robot(sy, rx, 0, time+1));
                        }
                        if(!visited[sy+1][rx][0]){
                            q.add(new Robot(sy+1, rx, 0, time+1));
                        }
                    }
                }
            }
        }
        
        return -1;
    }
    
    public int solution(int[][] board) {
        n = board.length;
        visited = new boolean[n][n][2];
        
        return bfs(board);
    }
}