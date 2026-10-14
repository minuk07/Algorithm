import java.util.*;

class Solution {
    
    int[] dy = {-1, 1, 0, 0}; //상 하 좌 우
    int[] dx = {0, 0, -1, 1};
    
    int n;
    int[][] board;
    int answer;
    
    class Node{
        int r, c, dir, cost;
        
        Node(int r, int c, int dir, int cost){
            this.r = r; this.c = c; this.dir = dir; this.cost = cost;
        }
    }
    
    private boolean inRange(int y, int x){
        return(y>=0 && x>=0 && y<n && x<n);
    }
    
    private void bfs(int y, int x){
        Queue<Node> q = new LinkedList<>();
        int[][][] visited = new int[n][n][4];
        
        for(int i=0; i<n; i++){
            for(int j=0;j <n; j++){
                Arrays.fill(visited[i][j], Integer.MAX_VALUE);
            }
        }
        
        visited[0][0][1] = 0;
        visited[0][0][2] = 0;
        q.add(new Node(0, 0, 1, 0));
        q.add(new Node(0, 0, 3, 0));
        
        while(!q.isEmpty()){
            Node cur = q.poll();
            
            if(cur.r == n-1 && cur.c == n-1){
               answer = Math.min(answer, visited[n-1][n-1][cur.dir]); 
            }
            
            for(int i=0; i<4; i++){
                int ny = cur.r + dy[i];
                int nx = cur.c + dx[i];
                
                int roadCost = (cur.dir == i ? 100 : 600);
                if(!inRange(ny, nx)) continue;
                if(board[ny][nx] == 1) continue;
                
                if(visited[ny][nx][i] > cur.cost + roadCost){
                    visited[ny][nx][i] = cur.cost + roadCost;
                    q.add(new Node(ny, nx, i, cur.cost + roadCost));    
                }
                
            }
        }
    }
    
    public int solution(int[][] board) {
        answer = Integer.MAX_VALUE;
        
        this.board = board;
        n = board.length;
        
        bfs(0, 0);
        
        return answer;
    }
}