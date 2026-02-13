import java.util.*;

class Solution {
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    
    static char[][] map;
    static int cnt;
    static int[][] dist;
    
    static int sy, sx;
    static int ey, ex;
    static int ly, lx;
    
    static class Node{
        int r,c;
        Node(int r, int c){
            this.r = r; this.c = c;
        }
    }
    
    static boolean inRange(int y, int x, char[][] map){
        return (y>=0 && x>=0 && y<map.length && x<map[0].length);
    }
    
    public int solution(String[] maps) {
        int answer = 0;
        
        map = new char[maps.length][maps[0].length()];
        
        for(int i=0; i<maps.length; i++){
            for(int j=0; j<maps[i].length(); j++){
                map[i][j] = maps[i].charAt(j);
                if(map[i][j] == 'S'){
                    sy = i; sx = j;
                }
                else if(map[i][j] == 'E'){
                    ey = i; ex = j;
                }
                else if(map[i][j] == 'L'){
                    ly = i; lx = j;
                }
            }
        }
        
        dist = new int[map.length][map[0].length];
        bfs(sy, sx, map);
        answer += dist[ly][lx];
        
        if(dist[ly][lx] == 0) return -1;
        
        dist = new int[map.length][map[0].length];
        bfs(ly, lx, map);
        answer += dist[ey][ex];
        
        if(dist[ey][ex] == 0) return -1;
        
                
        return answer;
    }
    
    static void bfs(int y, int x, char[][] map){
        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[map.length][map[0].length];

        
        q.add(new Node(y, x));
        visited[y][x] = true;
        dist[y][x] = 0;
        
        while(!q.isEmpty()){
            Node cur = q.poll();
            int cy = cur.r;
            int cx = cur.c;
            
            for(int i=0; i<4; i++){
                int ddy = cy + dy[i];
                int ddx = cx + dx[i];
                
                if(!inRange(ddy, ddx, map)) continue;
                if(map[ddy][ddx] == 'X') continue;
                if(visited[ddy][ddx]) continue;
                
                q.add(new Node(ddy, ddx));
                visited[ddy][ddx] = true;
                dist[ddy][ddx] = dist[cy][cx] + 1;
            }
        }
    }
}