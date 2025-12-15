import java.util.*;
import java.lang.*;
import java.io.*;


class Main {

    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};

    static int r;
    static int c;

    static int[][] map;
    static boolean[][] visited;
    static boolean[] check;
    
    static int result;

    public static void printList(int[][] arr){
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean inRange(int y, int x){
        return (y>=0 && x>=0 && y<r && x<c);
    }

    public static void dfs(int y, int x, int len){
        //System.out.println("y: "+ y + " x: "+ x+" len: "+ len);
        result = Math.max(result, len);
        visited[y][x] = true;
        check[map[y][x]] = true;

        for(int i=0; i<4; i++){
            int ddy = y + dy[i];
            int ddx = x + dx[i];
            
            if(!inRange(ddy,ddx)) continue;
            if(check[map[ddy][ddx]]) continue;
            if(!visited[ddy][ddx]){
                dfs(ddy, ddx, len + 1);
                visited[ddy][ddx] = false;
                check[map[ddy][ddx]] = false;
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        
        map = new int[r][c];
        visited = new boolean[r][c];
        check = new boolean[26];
        result = 0;

        for(int i=0; i<r; i++){
            String str = br.readLine();
            int j=0;
            for(char c : str.toCharArray()){
                map[i][j] = (int) c - 65;
                j++;
            }
        }

        dfs(0,0,1);
        
        System.out.print(result);

    }
}