import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};

    static int n,m;
    static int[][] map;
    static boolean[][] visited;

    static void printList(int[][] arr){
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

    static boolean inRange(int y, int x){
        return (y>=0 && x>=0 && y<n && x<m);
    }

    static void copyMap(int[][] temp, int[][] arr){
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(temp[i][j] > 0){
                    arr[i][j] = temp[i][j];
                }
                else{
                    arr[i][j] = 0;
                }
            }
        }
    }

    static void melt(int[][] map){
        int[][] temp = new int[n][m];

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                int cnt = 0;
                for(int d=0; d<4; d++){
                    int ddy = i + dy[d];
                    int ddx = j + dx[d];

                    if(!inRange(ddy, ddx)) continue;
                    if(map[ddy][ddx] == 0){
                        cnt++;
                    }
                }

                temp[i][j] = map[i][j] - cnt;
            }
        }

        copyMap(temp, map);
    }

    static boolean isMelt(){
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(map[i][j] > 0) return false;
            }
        }

        return true;
    }

    static void bfs(int y, int x){
        Queue<int[]> q = new LinkedList<>();
        visited[y][x] = true;
        q.add(new int[]{y, x});

        while(!q.isEmpty()){
            int[] cur = q.poll();

            for(int i=0; i<4; i++){
                int ddy = cur[0] + dy[i];
                int ddx = cur[1] + dx[i];

                if(!inRange(ddy, ddx)) continue;
                if(visited[ddy][ddx]) continue;
                if(map[ddy][ddx] == 0) continue;

                visited[ddy][ddx] = true;
                q.add(new int[]{ddy, ddx});
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

        
        int year = 0;

        while(!isMelt()){
            
            visited = new boolean[n][m];

            //System.out.println("year: " + year);
            
            int cnt = 0;
            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    if(!visited[i][j] && map[i][j] > 0){
                        bfs(i,j);
                        cnt++;
                    }
                }
            }

            //System.out.println("cnt: " + cnt);
            if(cnt > 1){
                System.out.println(year);
                return;
            }

            //printList(map);

            melt(map);

            year++;
        }

        System.out.println(0);
        
    }
}