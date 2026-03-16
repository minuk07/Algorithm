import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static long result;

    static int r, c, k;
    static int[][] map;

    static final int[] dy = {-1, 0, 1, 0};
    static final int[] dx = {0, 1, 0, -1};

    static final int[][] check = {
            {1,0}, {0,0}, {0,1}, {0,-1}, {-1,0}, {-1,-1}, {-1,1}, {-2,0}
    };

    static void printList(int[][] arr, int tc){
        System.out.println("#"+tc);
        for(int i=0; i<r+3; i++){
            for(int j=0; j<c; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static boolean inRange(int y, int x){
        return (y>=0 && x>=0 && y<r+3 && x<c);
    }

    static boolean canGo(int y, int x){

        for(int[] c : check){
            int ddy = y + c[0];
            int ddx = x + c[1];

            if(!inRange(ddy, ddx) || map[ddy][ddx] != 0) return false;
        }

        return true;
    }

    static int[] drop(int num, int x, int d){

        int y = 1;

        while(true){
            if(canGo(y+1, x)){
                y++;
            }
            else if(canGo(y+1, x-1)){
                y++;x--;
                d = (d - 1 + 4) % 4;
            }
            else if(canGo(y+1, x+1)){
                y++;x++;
                d = (d + 1) % 4;
            }else{
                break;
            }
        }

        if(y < 4){
            return new int[]{-1, -1};
        }

        map[y][x] = num;
        for(int i=0; i<4; i++){
            int ddy = y + dy[i];
            int ddx = x + dx[i];

            if(i == d){
                map[ddy][ddx] = -num;
            }else{
                map[ddy][ddx] = num;
            }
        }

        return new int[]{y, x};
    }

    static void reset(){
        for(int i=0; i<r+3; i++){
            for(int j=0; j<c; j++){
                map[i][j] = 0;
            }
        }
    }

    static int bfs(int y, int x){
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[r+3][c];

        visited[y][x] = true;
        q.add(new int[]{y, x});
        int row = y;

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

                if((Math.abs(map[ddy][ddx]) == Math.abs(map[cy][cx]))
                        || map[cy][cx] < 0){
                    q.add(new int[]{ddy, ddx});
                    visited[ddy][ddx] = true;
                    row = Math.max(row, ddy);
                }
            }
        }

        return row - 2;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        result = 0;

        map = new int[r+3][c];

        for(int tc=1; tc<=k; tc++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());

            int[] temp = drop(tc, x, d);

            if(temp[0] == -1){
                reset();
            }else{
                result += bfs(temp[0], temp[1]);
            }
            //printList(map, tc);
        }

        System.out.print(result);
    }
}