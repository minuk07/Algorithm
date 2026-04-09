import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static final int[] dy = {-1, 0, 1, 0}; // 0, 1, 2, 3
    static final int[] dx = {0, 1, 0, -1}; // 북 동 남 서

    static int n;
    static char[][] map;
    static int startY, startX;
    static int destY, destX;

    static class Mirror{
        int r, c, dir, cnt;

        Mirror(int r, int c, int dir, int cnt){
            this.r = r; this.c = c; this.dir = dir; this.cnt = cnt;
        }
    }

    static boolean inRange(int y, int x){
        return (y>=0 && x>=0 && y<n && x<n);
    }

    static int bfs(int y, int x){
        PriorityQueue<Mirror> pq = new PriorityQueue<>( (a, b) -> a.cnt - b.cnt);
        boolean[][][] visited = new boolean[n][n][4];

        for(int i=0; i<4; i++){
            //visited[y][x][i] = true;

            int ddy = y + dy[i];
            int ddx = x + dx[i];

            if(!inRange(ddy, ddx)) continue;
            if(map[ddy][ddx] == '*') continue;

            pq.add(new Mirror(y, x, i, 0));
        }

        while(!pq.isEmpty()){
            Mirror cur = pq.poll();

            int sy = cur.r; int sx = cur.c;
            int dir = cur.dir; int cnt = cur.cnt;
            visited[sy][sx][dir] = true;

            if(sy == destY && sx == destX){
                return cnt;
            }

            if(map[sy][sx] == '!'){
                if(dir % 2 == 0){ //상 하
                    for(int d=0; d<4; d++){
                        if(d==0 || d==2) continue;
                        int ddy = sy + dy[d];
                        int ddx = sx + dx[d];

                        if(!inRange(ddy, ddx)) continue;
                        if(map[ddy][ddx] == '*') continue;
                        if(visited[ddy][ddx][d]) continue;

                        pq.add(new Mirror(ddy, ddx, d, cnt + 1));
                    }
                }
                else{
                    for(int d=0; d<4; d++){
                        if(d==1 || d==3) continue;
                        int ddy = sy + dy[d];
                        int ddx = sx + dx[d];

                        if(!inRange(ddy, ddx)) continue;
                        if(map[ddy][ddx] == '*') continue;
                        if(visited[ddy][ddx][d]) continue;

                        pq.add(new Mirror(ddy, ddx, d, cnt + 1));
                    }
                }
            }

            int ddy = sy + dy[dir];
            int ddx = sx + dx[dir];
            if(!inRange(ddy, ddx) || map[ddy][ddx] == '*') continue;
            pq.add(new Mirror(ddy, ddx, dir, cnt));
        }


        return -1;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new char[n][n];

        boolean flag = true;
        
        for(int i=0; i<n; i++){
            String str = br.readLine();
            for(int j=0; j<n; j++){
                if(str.charAt(j) == '#'){
                    if(flag){
                        startY = i; startX = j; flag = false;
                    }
                    else{
                        destY = i; destX = j;
                    }
                }
                map[i][j] = str.charAt(j);
            }
        }
        
        System.out.println(bfs(startY, startX));
    }

    
}