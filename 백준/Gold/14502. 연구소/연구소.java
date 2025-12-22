import java.util.*;
import java.io.*;

public class Main {

    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {1, -1, 0, 0};

    static int n;
    static int m;
    static int[][] map;
    static int[][] temp;

    static int result;

    public static void printList(int[][] arr){
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean inRange(int y, int x){
        return (y >= 0 && x >= 0 && y < n && x < m);
    }

    public static int[][] copyMap(int[][] map){

        temp = new int[n][m];

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                temp[i][j] = map[i][j];
            }
        }

        return temp;
    }

    public static void dfs(int cnt){
        if(cnt == 3){
            solve();
            return;
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(map[i][j] == 0){
                    map[i][j] = 1;
                    dfs(cnt+1);
                    map[i][j] = 0;
                }
            }
        }
    }

    public static void solve(){
        temp = copyMap(map);

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(map[i][j] == 2){
                    bfs(i, j);
                }
            }
        }

        int sum = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(temp[i][j] == 0) sum++;
            }
        }
        result = Math.max(result, sum);
    }

    public static void bfs(int y, int x){
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];

        q.add(new int[]{y, x});
        visited[y][x] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int uy = cur[0];
            int ux = cur[1];

            for(int i=0; i<4; i++){
                int ddy = uy + dy[i];
                int ddx = ux + dx[i];

                if (!inRange(ddy, ddx)) continue;
                if (visited[ddy][ddx]) continue;
                if (map[ddy][ddx] == 1) continue;
                visited[ddy][ddx] = true;
                q.add(new int[]{ddy, ddx});
                temp[ddy][ddx] = 2;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result = 0;

        dfs(0);

        System.out.println(result);
    }

}