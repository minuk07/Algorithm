import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static int n,m,k;
    static int[][] map;
    static boolean[][] visited;
    static int cnt;

    static boolean inRange(int y, int x){
        return (y>=0 && x>=0 && y<n && x<m);
    }

    static void dfs(int y, int x){
        visited[y][x] = true;
        cnt++;

        for(int i=0; i<4; i++){
            int ddy = y + dy[i];
            int ddx = x + dx[i];

            if(!inRange(ddy, ddx)) continue;
            if(map[ddy][ddx] == 0) continue;
            if(visited[ddy][ddx]) continue;

            dfs(ddy, ddx);
        }
    }
    
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];

        for(int i=0; i<k; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            map[r][c] = -1;
        }

        int answer = 0;

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(!visited[i][j] && map[i][j] == -1){
                    cnt = 0;
                    dfs(i, j);
                    answer = Math.max(cnt, answer);
                }
            }
        }

        System.out.println(answer);
    }
}