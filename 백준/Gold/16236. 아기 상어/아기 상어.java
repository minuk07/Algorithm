import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};

    static int n;
    static int[][] map;
    static Shark shark;
    static int t;

    static class Shark{
        int r, c, size, cnt;

        Shark(int r, int c, int size){
            this.r = r; this.c = c; this.size = size;
            this.cnt = 0;
        }
    }

    static void printList(int[][] arr){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static boolean inRange(int y, int x){
        return (y>=0 && x>=0 && y<n && x<n);
    }

    static int bfs(Shark shark){

        Queue<int[]> q = new LinkedList<>();
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> {
            if(a[2] != b[2]) return a[2] - b[2];
            if(a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });
        
        boolean[][] visited = new boolean[n][n];

        int size = shark.size;

       // System.out.println("size: "+ size);
        
        visited[shark.r][shark.c] = true;
        q.add(new int[]{shark.r, shark.c, 0});
        
        while(!q.isEmpty()){
            int[] cur = q.poll();

            int cy = cur[0]; int cx = cur[1]; int dist = cur[2];
            
            for(int i=0; i<4; i++){
                int ddy = cy + dy[i];
                int ddx = cx + dx[i];

                if(!inRange(ddy, ddx)) continue;
                if(map[ddy][ddx] > size) continue;
                if(visited[ddy][ddx]) continue;
                
                visited[ddy][ddx] = true;
                q.add(new int[]{ddy, ddx, dist + 1});

                if(map[ddy][ddx] < size && map[ddy][ddx] != 0){
                    pq.add(new int[]{ddy, ddx, dist + 1});
                }
            }
        }

        if(!pq.isEmpty()){
            int[] target = pq.poll();

            int ty = target[0]; int tx = target[1]; int dist = target[2];

            map[shark.r][shark.c] = 0;
            map[ty][tx] = 9;

            shark.r = ty;
            shark.c = tx;
            shark.cnt++;

            if(shark.cnt >= size){
                shark.size++;
                shark.cnt = 0;
            }

            return dist;
        }

        return 0;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        t = 0;
        
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 9){
                    shark = new Shark(i, j, 2);
                }
            }
        }

        while(true){
            int time = bfs(shark);
            if(time == 0) break;
            t += time;
        }

        System.out.println(t);
    }
}