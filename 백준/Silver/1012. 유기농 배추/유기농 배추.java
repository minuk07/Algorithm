import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    static int t, n, m, k;
    static int[][] map;
    static boolean[][] visited;

    static boolean inRange(int y, int x){
        return (y>=0 && x>=0 && y<n && x<m);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());
        
        for(int tc = 0; tc<t; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
        
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            map = new int[n][m];

            for(int i=0; i<k; i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[x][y] = 1;
            }

            visited = new boolean[n][m];

            int cnt = 0;

            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    if(map[i][j] == 1 && !visited[i][j]) {
                        bfs(i,j);
                        cnt++;
                    }
                }
            }

            System.out.println(cnt);
        }
    }

    static void bfs(int y, int x){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{y, x});
        visited[y][x] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cy = cur[0]; int cx = cur[1];

            for(int i=0; i<4; i++){
                int ddy = cy + dy[i];
                int ddx = cx + dx[i];

                if(!inRange(ddy, ddx)) continue;
                if(map[ddy][ddx] == 0) continue;
                if(visited[ddy][ddx]) continue;

                q.add(new int[]{ddy, ddx});
                visited[ddy][ddx] = true;
            }
        }
    }
}