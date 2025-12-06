
import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int m;
    static int r;

    static int[][] domino;
    static char[][] map;

    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

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

    public static void printList(char[][] arr){
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean inRange(int y, int x){
        return (y>=0 && x>=0 && y<n && x<m);
    }

    public static int getDir(char c){
        if(c == 'E') return 0;
        else if(c == 'W') return 1;
        else if(c == 'S') return 2;
        else return 3;
    }

    public static void attack(int y, int x, char c){
        int dir = getDir(c);

        int h = domino[y][x];
        map[y][x] = 'F';
        result++;

        int ddy = y + dy[dir];
        int ddx = x + dx[dir];

        while(inRange(ddy,ddx)){
            //System.out.println("y: "+ddy + " x: "+ddx);
            h--;

            if(map[ddy][ddx] == 'F') {
                if(h == 1) break;
                ddy += dy[dir];
                ddx += dx[dir];
                continue;
            }

            result++;
            map[ddy][ddx] = 'F';

            if(h == 1){
                //System.out.println("y: "+ddy + " x: "+ddx);
                //map[ddy][ddx] = 'F';
                if(domino[ddy][ddx] == 1) break;
            }

            h = Math.max(h, domino[ddy][ddx]);
            ddy += dy[dir];
            ddx += dx[dir];
        }
    }

    public static void defense(int y, int x){
        map[y][x] = 'S';
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        domino = new int[n][m];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                domino[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        map = new char[n][m];
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                map[i][j] = 'S';
            }
        }

        for(int tc = 1; tc <= r; tc++){
            //System.out.println("#"+tc);
            st = new StringTokenizer(br.readLine());
            int ay = Integer.parseInt(st.nextToken()) - 1;
            int ax = Integer.parseInt(st.nextToken()) - 1;
            char dir = st.nextToken().charAt(0);

            st = new StringTokenizer(br.readLine());
            int sy = Integer.parseInt(st.nextToken()) - 1;
            int sx = Integer.parseInt(st.nextToken()) - 1;

            attack(ay, ax, dir);
            //System.out.println("attack");
            //printList(domino);
            //printList(map);
            defense(sy, sx);
            //System.out.println("defense");
            //printList(domino);
            //printList(map);
        }

        System.out.println(result);
        printList(map);
    }
}