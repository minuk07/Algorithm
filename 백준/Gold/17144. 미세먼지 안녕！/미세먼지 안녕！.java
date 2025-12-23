import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int[] dy = {-1, 1, 0, 0}; //상 하 좌 우
    static int[] dx = {0, 0, -1, 1};

    static int[] upY = {0, -1, 0, 1}; //우 상 좌 하
    static int[] upX = {1, 0, -1, 0};

    static int[] downY = {0, 1, 0, -1}; //우 하 좌 상
    static int[] downX = {1, 0, -1, 0};

    static int r;
    static int c;
    static int t;

    static int[][] map;
    static Cleaner cleaner;

    static void printList(int[][] arr){
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static class Cleaner{
        int ur,dr;
        Cleaner(int dr){
            this.ur = dr - 1; this.dr = dr;
        }
    }

    static boolean inRange(int y, int x){
        return (y>=0 && x>=0 && y<r && x<c);
    }


    public static void spread(){
        int[][] temp = new int[r][c];

        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(map[i][j] >= 5){
                    int cnt = 0;
                    int tmp = map[i][j];
                    int spreadAmount = map[i][j] / 5;
                    for(int d=0; d<4; d++){
                        int ddy = i + dy[d];
                        int ddx = j + dx[d];

                        if(!inRange(ddy, ddx)) continue;
                        if(map[ddy][ddx] == -1) continue;
                        
                        temp[ddy][ddx] += spreadAmount;
                        cnt++;
                    }
                    map[i][j] = tmp - (cnt*spreadAmount);
                }
            }
        }

        for(int i=0; i<r; i++){
            for(int j=0;j<c; j++){
                if(map[i][j] == 0){
                    map[i][j] = temp[i][j];
                }
                else{
                    map[i][j] += temp[i][j];
                }
            }
        }
    }

    public static void move(){
        int[][] temp = new int[r][c];
        
        int uy = cleaner.ur;
        int ux = 1;
        int upDir = 0;
        int dy = cleaner.dr;
        int dx = 1;
        int downDir = 0;

        int tmp = 0;
        
        while(ux != 0 || uy != cleaner.ur){
            int cur = map[uy][ux];
            int ddy = uy + upY[upDir];
            int ddx = ux + upX[upDir];
            if(!inRange(ddy, ddx)){
                upDir = (upDir+1) % 4;
                continue;
            }
            map[uy][ux] = tmp;
            tmp = cur;
            uy = ddy;
            ux = ddx;
        }

        tmp = 0;

        while(dx != 0 || dy != cleaner.dr){
            int cur = map[dy][dx];
            int ddy = dy + downY[downDir];
            int ddx = dx + downX[downDir];
            if(!inRange(ddy, ddx)){
                downDir = (downDir+1)%4;
                continue;
            }
            map[dy][dx] = tmp;
            tmp = cur;
            dy = ddy;
            dx = ddx;
        }

        map[cleaner.ur][0] = -1;
        map[cleaner.dr][0] = -1;

        //printList(map);

    }
    
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        map = new int[r][c];

        for(int i=0; i<r; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<c; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == -1){
                    if(i>=1) cleaner = new Cleaner(i);
                }
            }
        }

        for(int i=0; i<t; i++){
            spread();
            move();
        }

        int result = 0;

        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(map[i][j] > 0){
                    result += map[i][j];
                }
            }
        }

        System.out.println(result);
        //printList(map);
        
    }
}