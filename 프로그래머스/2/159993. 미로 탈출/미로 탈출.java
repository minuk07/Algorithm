import java.util.*;

class Solution {
    
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    
    static int sy, sx, ey, ex, ly, lx;
    static int[][] map;
    
    static int n, m;
    
    static boolean inRange(int y, int x){
        return (y>=0 && x>=0 && y<n && x<m);
    }
    
    static int bfs(int y, int x){
        Queue<int[]> q = new LinkedList<>();
        boolean[][][] visited = new boolean[n][m][2];
        
        visited[y][x][0] = true;
        q.add(new int[]{y, x, 0, 0});
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            
            if(map[cur[0]][cur[1]] == 2 && cur[2] == 1){
                return cur[3];
            }
            
            for(int i=0; i<4; i++){
                int ny = cur[0] + dy[i];
                int nx = cur[1] + dx[i];
                
                if(!inRange(ny, nx)) continue;
                if(visited[ny][nx][cur[2]]) continue;
                if(map[ny][nx] == -1) continue;
                
                if(map[ny][nx] == 3 && cur[2] == 0){
                    visited[ny][nx][1] = true;
                    q.add(new int[]{ny, nx, 1, cur[3] + 1});
                }else{
                    visited[ny][nx][cur[2]] = true;
                    q.add(new int[]{ny, nx, cur[2], cur[3] + 1});
                }
            }
        }
        
        return -1;
    }
    
    public int solution(String[] maps){
        
        n = maps.length;
        m = maps[0].length();
        
        map = new int[n][m];
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(maps[i].charAt(j) == 'S'){
                    sy = i; sx = j;
                    map[i][j] = 1;
                }else if(maps[i].charAt(j) == 'E'){
                    ey = i; ex = j;
                    map[i][j] = 2;
                }else if(maps[i].charAt(j) == 'L'){
                    ly = i; lx = j;
                    map[i][j] = 3;
                }else if(maps[i].charAt(j) == 'O'){
                    map[i][j] = 0;
                }else{
                    map[i][j] = -1;
                }
            }
        }
        
        return bfs(sy, sx);
    }
}