import java.util.*;

class Solution {
    
    int[] dy = {0, 0, -1, 1};
    int[] dx = {1, -1, 0, 0};
    
    int n, m;
    String[] board;
    
    class Robot{
        int r, c, cnt;
        Robot(int r, int c, int cnt){
            this.r=r; this.c=c; this.cnt=cnt;
        }
    }
    
    private boolean inRange(int y, int x){
        return(y>=0 && x>=0 && y<n && x<m);
    }
    
    private int bfs(Robot start){
        boolean[][] visited = new boolean[n][m];
        Queue<Robot> q = new LinkedList<>();
        visited[start.r][start.c] = true;
        q.add(start);
        
        while(!q.isEmpty()){
            Robot cur = q.poll();
            if(board[cur.r].charAt(cur.c) == 'G'){
                return cur.cnt;
            }
            
            for(int i=0; i<4; i++){
                int ny = cur.r + dy[i];
                int nx = cur.c + dx[i];
                
                while(inRange(ny, nx) && board[ny].charAt(nx) != 'D'){
                    ny += dy[i];
                    nx += dx[i];
                }
                
                ny -= dy[i];
                nx -= dx[i];
                
                if(!visited[ny][nx]){
                    visited[ny][nx] = true;
                    q.add(new Robot(ny, nx, cur.cnt + 1));
                }
            }
        }
        
        return -1;
    }
    
    public int solution(String[] board) {
        int answer = -1;
        
        this.board = board;
        n = board.length;
        m = board[0].length();
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(board[i].charAt(j) == 'R'){
                    Robot start = new Robot(i, j, 0);
                    answer = bfs(start);
                }
            }
        }
        
        return answer;
    }
}