import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {1, -1, 0, 0};

    static int n;
    static int m;
    static int[][] map;
    static int[][][] dist;

    public static void printList(int[][] arr){
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printList(int[][][] arr, int boom){
        System.out.println(">>>>>boom: " + boom);
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                System.out.print(arr[i][j][boom] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean inRange(int y , int x){
        return (y>=0 && x>=0 && y<n && x<m);
    }

    public static void bfs(int y, int x){
        Queue<int[]> q = new LinkedList<>();
        boolean[][][] visited = new boolean[n][m][2];
        q.add(new int[]{0,0,0});
        visited[0][0][0] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int uy = cur[0];
            int ux = cur[1];
            int boom = cur[2];

            for(int i=0; i<4; i++){
                int ddy = uy + dy[i];
                int ddx = ux + dx[i];

                if(!inRange(ddy, ddx)) continue;
                if(boom == 0){
                    if(map[ddy][ddx] == 1){
                        if(!visited[ddy][ddx][1]){
                            visited[ddy][ddx][1] = true;
                            dist[ddy][ddx][1] = dist[uy][ux][boom] + 1;
                            q.add(new int[]{ddy, ddx, 1});
                        }
                        else continue;
                    }
                    else{
                        if(!visited[ddy][ddx][0]){
                            visited[ddy][ddx][0] = true;
                            dist[ddy][ddx][0] = dist[uy][ux][boom] + 1;
                            q.add(new int[]{ddy, ddx, 0});
                        }
                    }
                }
                else{ //이미 벽을 부순 상태
                    if(map[ddy][ddx] == 1) continue;
                    if(!visited[ddy][ddx][1]){
                        visited[ddy][ddx][1] = true;
                        dist[ddy][ddx][1] = dist[uy][ux][boom] + 1;
                        q.add(new int[]{ddy, ddx, 1});
                    }
                }
                
            }
        }
    }
    
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for(int i=0; i<n; i++){
            String str = br.readLine();
            for(int j=0; j<m; j++){
                map[i][j] = str.charAt(j) - '0';
            }
        }

        dist = new int[n][m][2];
        dist[0][0][0] = 1;
        dist[0][0][1] = 1;

        //printList(map);

        bfs(0,0);

        //printList(dist, 0);
        //printList(dist, 1);

        int result = -1;
        
        if(dist[n-1][m-1][0] == 0 && dist[n-1][m-1][1] == 0){
            result = -1;
        }
        else{
            if(dist[n-1][m-1][0] == 0 && dist[n-1][m-1][1] != 0){
                result = dist[n-1][m-1][1];
            }
            else if(dist[n-1][m-1][0] != 0 && dist[n-1][m-1][1] == 0){
                result = dist[n-1][m-1][0];
            }
            else{
                result = Math.min(dist[n-1][m-1][0], dist[n-1][m-1][1]);
            }
        }

        System.out.println(result);
        
    }
}