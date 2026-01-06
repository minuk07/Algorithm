import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};

    static int r;
    static int c;

    static int sy;
    static int sx;

    static int[][] map;
    static int result;
    static int[][] fireDist;
    static int[][] dist;
    
    static Queue<int[]> fq = new LinkedList<>();

    static void printList(int[][] arr){
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static boolean inRange(int y, int x){
        return (y>=0 && x>=0 && y<r && x<c);
    }

    static void fireBfs(){

        while(!fq.isEmpty()){
            int[] cur = fq.poll();
            int uy = cur[0];
            int ux = cur[1];

            for(int i=0; i<4; i++){
                int ddy = uy + dy[i];
                int ddx = ux + dx[i];
                
                if(!inRange(ddy, ddx)) continue;
                if(fireDist[ddy][ddx] > 0) continue;
                if(map[ddy][ddx] == -1) continue;
                fq.add(new int[]{ddy, ddx});
                fireDist[ddy][ddx] = fireDist[uy][ux] + 1;
            }
        }
    }

    static void bfs(int y, int x){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{y, x});

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int uy = cur[0];
            int ux = cur[1];

            for(int i=0; i<4; i++){
                int ddy = uy + dy[i];
                int ddx = ux + dx[i];

                if(!inRange(ddy, ddx)){
                    System.out.print(dist[uy][ux]);
                    return;
                }
                if(dist[ddy][ddx] > 0) continue;
                if(map[ddy][ddx] == -1) continue;
                if(fireDist[ddy][ddx] != 0 && fireDist[ddy][ddx] <= dist[uy][ux] + 1) continue;
                q.add(new int[]{ddy, ddx});
                dist[ddy][ddx] = dist[uy][ux] + 1;
            }
        }

        System.out.println("IMPOSSIBLE");
    }
    
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new int[r][c];
        fireDist = new int[r][c];
        dist = new int[r][c];
        
        for(int i=0; i<r; i++){
            String str = br.readLine();
            for(int j=0; j<c; j++){
                int num = -1;
                char tmp = str.charAt(j);
                if(tmp == 'J'){
                    sy = i; sx = j;
                    dist[sy][sx] = 1;
                }
                else if(tmp == '#'){
                    map[i][j] = -1;
                }   
                else if(tmp == 'F'){
                    fq.add(new int[]{i,j});
                    fireDist[i][j] = 1;
                }
                else continue;
            }
        }

        fireBfs();

        bfs(sy, sx);        
        

    }
}