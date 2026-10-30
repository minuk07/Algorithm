import java.util.*;

class Solution {
    
    final int INF = 1_000_000;
    
    int[] dy = {-1, 1, 0, 0};
    int[] dx = {0, 0, -1, 1};
    
    int n,m;
    int[][][] visited;
    int[][] board;
    int answer;
    
    class Road{
        int r, c, dir, cost;
        
        Road(int r, int c, int dir, int cost){
            this.r=r; this.c=c; this.dir=dir; this.cost=cost;
        }
    }
    
    private boolean inRange(int y, int x){
        return(y>=0 && x>=0 && y<n && x<m);
    }
    
    private void bfs(int y, int x){
        Queue<Road> q = new LinkedList<>();
        q.add(new Road(y, x, 3, 0));
        q.add(new Road(y, x, 1, 0));
        
        while(!q.isEmpty()){
            Road cur = q.poll();
            
            if(cur.r==n-1 && cur.c==m-1){
                answer = Math.min(answer, cur.cost);
            }
            
            for(int i=0; i<4; i++){
                int ny = cur.r + dy[i];
                int nx = cur.c + dx[i];
                
                if(!inRange(ny, nx)) continue;
                if(board[ny][nx] == 1) continue;
                
                int make = (cur.dir == i ? 100 : 600);
                if(visited[ny][nx][i] > cur.cost + make){
                    visited[ny][nx][i] = cur.cost + make;
                    q.add(new Road(ny, nx, i, cur.cost + make));
                }
            }
        }
    }
    
    public int solution(int[][] board) {
        answer = INF;
        
        this.board=board;
        n = board.length;
        m = board[0].length;
        
        visited = new int[n][m][4];
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                Arrays.fill(visited[i][j], INF);
            }
        }
        
        for(int i=0; i<4; i++){
            visited[0][0][i] = 0;
        }
        
        bfs(0,0);
        
        return answer;
    }
}