import java.util.*;

class Solution {
    
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    
    static int answer;
    
    static int n, m;
    static int[][] map;
    static int startY, startX;
    static int destY, destX;
    
    static class Robot{
        int r, c, cnt;
        
        Robot(int r, int c,int cnt){
            this.r = r; this.c = c; this.cnt = cnt;
        }
    }
    
    static boolean inRange(int y, int x){
        return (y>=0 && x>=0 && y<n && x<m);
    }
    
    static void bfs(Robot robot){
        
        boolean[][] visited = new boolean[n][m];
        Queue<Robot> q = new LinkedList<>();
        q.add(robot);
        visited[robot.r][robot.c] = true;
        
        while(!q.isEmpty()){
            Robot cur = q.poll();
            
            int y = cur.r; int x = cur.c; int cnt = cur.cnt;
            
            if(y == destY && x == destX){
                answer = cnt;
                break;
            }
            
            for(int i=0; i<4; i++){
            
                int ny = y + dy[i];
                int nx = x + dx[i];

                while(inRange(ny, nx) && map[ny][nx] == 0){
                    ny += dy[i];
                    nx += dx[i];
                }

                ny -= dy[i];
                nx -= dx[i];

                if(visited[ny][nx]) continue;

                visited[ny][nx] = true;
                q.add(new Robot(ny, nx, cnt + 1));
            }
        }
        
        
        
        return;
    }
    
    public int solution(String[] board) {
        answer = 0;
        
        n = board.length;
        m = board[0].length();
        map = new int[n][m];
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                char tmp = board[i].charAt(j);
                
                if(tmp == 'R'){
                    startY = i; startX = j;
                }else if(tmp == 'G'){
                    destY = i; destX = j;
                }else if(tmp == 'D'){
                    map[i][j] = -1;
                }else{
                    map[i][j] = 0;
                }
            }
        }
        
        bfs(new Robot(startY, startX, 0));
        
        if(answer != 0) return answer;
        
        return -1;
    }
}