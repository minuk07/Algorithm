import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static int n,m;
    static int[][] map;
    static int maxLevel = 0;

    static boolean inRange(int y, int x){
        return (y>=0 && x>=0 && y<n && x<m);
    }

    static boolean bfs(int y, int x, int level){
        boolean[][][] visited = new boolean[n][m][2];
        Queue<int[]> q = new LinkedList<>();

        if(map[y][x] > level) return false;

        visited[y][x][0] = true;
        q.add(new int[]{y, x, 0});

        while(!q.isEmpty()){
            int[] cur = q.poll();

            int cy = cur[0]; int cx = cur[1]; int used = cur[2];
            if(cy == n-1 && cx == m-1) return true;

            for(int i=0; i<4; i++){
                int ddy = cy + dy[i];
                int ddx = cx + dx[i];

                if(inRange(ddy, ddx) && !visited[ddy][ddx][used] && map[ddy][ddx] <= level){
                    visited[ddy][ddx][used] = true;
                    q.add(new int[]{ddy, ddx, used});
                }

                int sy = cy + 2*dy[i];
                int sx = cx + 2*dx[i];

                if(used == 0) {
                    if(inRange(sy, sx) && !visited[sy][sx][1] && map[sy][sx] <= level){
                        visited[sy][sx][1] = true;
                        q.add(new int[]{sy, sx, 1});
                    }
                }
            }
        }

        return false;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m =Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                maxLevel = Math.max(map[i][j], maxLevel);
            }
        }

        int left = 0; int right = maxLevel;
        int answer = maxLevel;

        while(left <= right){
            int mid = (left + right) / 2;

            if(bfs(0, 0, mid)){
                answer = mid;
                right = mid - 1;
            }
            else{
                left = mid + 1;
            }
        }

        System.out.println(answer);
        
    }
}