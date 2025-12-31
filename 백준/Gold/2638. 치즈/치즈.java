import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};

    static int n;
    static int m;

    static int[][] map;
    static int[][] outside;

    static void printList(int[][] arr){
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static boolean inRange(int y, int x){
        return (y>=0 && x>=0 && y<n && x<m);
    }

    static boolean cleanMap(int[][] arr){
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(map[i][j] == 1) return false;
            }
        }

        return true;
    }

    static int[][] copyList(int[][] arr){
        int[][] temp = new int[n][m];
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                temp[i][j] = arr[i][j];
            }
        }
        return temp;
    }

    public static void bfs(int y, int x){
        outside = new int[n][m];
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> q = new LinkedList<>();
        visited[y][x] = true;
        outside[y][x] = -1;
        q.add(new int[]{y, x});

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int uy = cur[0];
            int ux = cur[1];

            for(int i=0; i<4; i++){
                int ddy = uy + dy[i];
                int ddx = ux + dx[i];

                if(!inRange(ddy,ddx)) continue;
                if(map[ddy][ddx] == 1) continue;
                if(visited[ddy][ddx]) continue;
                visited[ddy][ddx] = true;
                q.add(new int[]{ddy, ddx});
                outside[ddy][ddx] = -1;
            }
        }

    }

    public static void melt(){

        bfs(0,0);

        int[][] temp = new int[n][m];
        temp = copyList(map);
        
        for(int i=1; i<n-1; i++){
            for(int j=1; j<m-1; j++){
                if(map[i][j] == 0) continue;
                int cnt = 0;
                for(int d=0; d<4; d++){
                    int ddy = i + dy[d];
                    int ddx = j + dx[d];
                    if(map[ddy][ddx] == 1) continue;
                    if(outside[ddy][ddx] == -1) cnt++;
                }
                if(cnt >= 2){
                    temp[i][j] = 0;
                }
            }
            
        }

        map = copyList(temp);

        //printList(map);
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

        int result=0;
        while(!cleanMap(map)){
            melt();
            result++;
        }

        System.out.print(result);
    }
}