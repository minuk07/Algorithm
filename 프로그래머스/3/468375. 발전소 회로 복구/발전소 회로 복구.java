import java.util.*;

class Solution {
    
    final int INF = Integer.MAX_VALUE;
    
    int[] dy = {0, 0, -1, 1};
    int[] dx = {1, -1, 0, 0};
    
    int n, m, k;
    int ey, ex;
    
    int[][] dist;
    int[][] panels;
    String[] grid;
    
    private boolean inRange(int y, int x){
        return(y>=0 && x>=0 && y<n && x<m);
    }
    
    private void bfs(int y, int x, int num){
        
        int[][] map = new int[n][m];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{y, x, 0});
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            
            for(int i=0; i<4; i++){
                int ny = cur[0] + dy[i];
                int nx = cur[1] + dx[i];
                
                if(!inRange(ny, nx)) continue;
                if(ny == y && nx == x) continue;
                if(map[ny][nx] > 0) continue;
                if(grid[ny].charAt(nx) == '#') continue;
                
                map[ny][nx] = cur[2] + 1;
                q.add(new int[]{ny, nx, cur[2] + 1});
            }
        }
        
        dist[0][num] = map[ey][ex];
        dist[num][0] = map[ey][ex];
        
        for(int i=0; i<k; i++){
            int panelNum = i+1;
            int[] panel = panels[i];
            
            dist[num][panelNum] = map[panel[1]-1][panel[2]-1];
        }
    }
    
    public int solution(int h, String[] grid, int[][] panels, int[][] seqs) {
        int answer = INF;
        
        this.panels = panels;
        this.grid = grid;
        
        n = grid.length;
        m = grid[0].length();
        k = panels.length;
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(grid[i].charAt(j) == '@'){
                    ey = i; ex = j;
                }
            }
        }
        
        dist = new int[k+1][k+1];
        
        int idx = 1;
        
        for(int[] panel : panels){
            bfs(panel[1]-1 , panel[2]-1, idx++);
        }
        
        int[] need = new int[k+1];
        
        for(int[] seq : seqs){
            int p1 = seq[0]; int p2 = seq[1];
            need[p2] |= (1 << (p1-1));
        }
        
        int[][] dp = new int[1 << k][k+1];
        for(int i=0; i<1 << k; i++){
            Arrays.fill(dp[i], INF);
        }
        dp[0][1] = 0;
        
        for(int mask = 0; mask < 1<<k; mask++){
            for(int last = 1; last <= k; last++){
                
                if(dp[mask][last] == INF) continue;
                
                for(int next = 1; next<=k; next++){
                    if((mask & (1 << next-1)) != 0) continue;
                    if((need[next] & ~mask) != 0) continue;
                    
                    int nextMask = mask | (1 << (next-1));
                    int gap = Math.abs(panels[next-1][0] - panels[last-1][0]);
                    int cost = (gap == 0 ? dist[last][next] : dist[0][last] + dist[0][next] + gap);
                    dp[nextMask][next] = Math.min(dp[nextMask][next], dp[mask][last] + cost);
                }
            }
        }
        
        for(int i=1; i<=k; i++){
            answer = Math.min(answer, dp[(1<<k)-1][i]);
        }
        
        return answer;
    }
}