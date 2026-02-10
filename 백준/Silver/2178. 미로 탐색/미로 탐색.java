import java.util.*;
import java.io.*;

class Main {

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static int n,m;
    static int[][] map;
    static int[][] dist;

    static int cnt;

    static boolean inRange(int y, int x){
        return (y>=0 && x>=0 && y<n && x<m);
    }
    
    public static void main(String[] args) throws IOException {
    
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        dist = new int[n][m];

        for(int i=0; i<n; i++){
            String tmp = br.readLine();
            for(int j=0; j<m; j++){
                map[i][j] = tmp.charAt(j) - '0';
            }
        }

        cnt = 0;

        bfs(0,0);

        System.out.println(dist[n-1][m-1]);
    }

    static void bfs(int y, int x){
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];

        q.add(new int[]{y, x});
        visited[y][x] = true;

        dist[y][x] = 1;
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cy = cur[0];
            int cx = cur[1];

            for(int i=0; i<4; i++){
                int ddy = cy + dy[i];
                int ddx = cx + dx[i];

                if(!inRange(ddy, ddx)) continue;
                if(map[ddy][ddx] == 0) continue;
                if(visited[ddy][ddx]) continue;
                visited[ddy][ddx] = true;
                q.add(new int[]{ddy, ddx});
                dist[ddy][ddx] = dist[cy][cx] + 1;
            }
        }
    }
}