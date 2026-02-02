import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static List<int[]> list = new ArrayList<>();

    static int r;
    static int c;
    static char[][] map;

    static int maxY, maxX, minY, minX;

    static void printList(char[][] map){
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                System.out.print(map[i][j]+ " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static boolean inRange(int y, int x){
        return (y>=0 && x>=0 && y<r && x<c);
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];
        
        for(int i=0; i<r; i++){
            String str = br.readLine();
            for(int j=0; j<c; j++){
                map[i][j] = str.charAt(j);
            }
        }

        //printList(map);

        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(map[i][j] == 'X') check(i, j);
            }
        }

        for(int[] l : list){
            map[l[0]][l[1]] = '.';
        }

        maxY = Integer.MIN_VALUE;
        maxX = Integer.MIN_VALUE;
        minY = Integer.MAX_VALUE;
        minX = Integer.MAX_VALUE;

        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(map[i][j] == 'X'){
                    minY = Math.min(minY, i);
                    minX = Math.min(minX, j);
                    maxY = Math.max(maxY, i);
                    maxX = Math.max(maxX, j);
                }
            }
        }

        for(int i=minY; i<=maxY; i++){
            for(int j=minX; j<=maxX; j++){
                System.out.print(map[i][j]);
            }
            System.out.println();
        }

        //printList(map);
    }

    static void check(int y, int x){
        int cnt = 0;

        for(int i=0; i<4; i++){
            int ddy = y + dy[i];
            int ddx = x + dx[i];

            if(!inRange(ddy, ddx) || map[ddy][ddx] == '.') cnt++;
            
        }

        if(cnt >= 3){
            list.add(new int[]{y, x});
        }
    }
}