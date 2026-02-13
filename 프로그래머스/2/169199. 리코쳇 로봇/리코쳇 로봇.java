import java.util.*;

class Solution {
    
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    
    static int sy, sx;
    static int answer;
    
    static int n, m;
    static char[][] map;
    
    static class Node{
        int r,c;
        Node(int r, int c){
            this.r = r; this.c = c;
        }
    }
    
    static boolean inRange(int y, int x){
        return (y>=0 && x>=0 && y<n && x<m);
    }
    
    public int solution(String[] board) {
        
        n = board.length;
        m = board[0].length();
        
        map = new char[n][m];
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                map[i][j] = board[i].charAt(j);
                if(map[i][j] == 'R'){
                    sy = i; sx =j;
                }
            }
        }
        
        answer = -1;
        
        bfs(sy, sx);
        
        return answer;
    }
    
    static void bfs(int y, int x){
        Queue<Node> q = new LinkedList<>();
        int[][] dist = new int[n][m];
        boolean[][] visited = new boolean[n][m];
        visited[y][x] = true;
        q.add(new Node(y, x));
        
        while(!q.isEmpty()){
            Node cur = q.poll();
            int cy = cur.r;
            int cx = cur.c;
            
            if(map[cy][cx] == 'G'){
                answer = dist[cy][cx];
                break;
            }
            
            for(int i=0; i<4; i++){
                int ddy = cy + dy[i];
                int ddx = cx + dx[i];
                
                while(inRange(ddy, ddx) && map[ddy][ddx] != 'D'){
                    ddy = ddy + dy[i];
                    ddx = ddx + dx[i];
                }
                ddy -= dy[i];
                ddx -= dx[i];
                
                if(visited[ddy][ddx]) continue;
                visited[ddy][ddx] = true;
                dist[ddy][ddx] = dist[cy][cx] + 1;
                q.add(new Node(ddy,ddx));
            }
        }
    }
}