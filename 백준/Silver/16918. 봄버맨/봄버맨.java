
import java.util.*;
import java.io.*;

public class Main {
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {1, -1, 0, 0};
    static int R;
    static int C;
    static int N;
    static int[][] bomb;
    static char[][] map;
    static int cnt = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        bomb = new int[R][C];

        cnt = 1;

        for(int i=0; i<R; i++){
            String str = br.readLine();
            for(int j=0; j<C; j++){
                map[i][j] = str.charAt(j);
                if(str.charAt(j) == 'O') bomb[i][j] = 2;
                else{bomb[i][j] = 0;}
            }
        }

        while(true){
            if(cnt == N) break;

            cnt++;
            if(cnt % 2 == 0){ //폭탄 다 채우는 경우
                setBomb();
            }
            else{ //폭탄 터트리기
                for(int i=0; i<R; i++){
                    for(int j =0; j<C; j++){
                        if(bomb[i][j] == 1) { //터지는 경우
                            bfs(i, j);
                        }
                        else{
                            bomb[i][j] --;
                        }
                    }
                }

                setDefaultBomb();
            }

//            for(int i=0; i<R; i++){
//                StringBuilder sb = new StringBuilder();
//                for(int j=0; j<C; j++){
//                    sb.append(map[i][j]);
//                }
//                System.out.println(sb.toString());
//
//            }
//            System.out.println(cnt+ "초 후==============================");

        }

        for(int i=0; i<R; i++){
            StringBuilder sb = new StringBuilder();
            for(int j=0; j<C; j++){
                sb.append(map[i][j]);
            }
            System.out.println(sb.toString());
        }
    }

    //폭탄터짐
    public static void bfs(int y, int x){
        map[y][x] = '.';

        for(int i=0; i<4; i++){
            int uy = y + dy[i];
            int ux = x + dx[i];

            if(uy<0 || ux<0 || uy >= R || ux >= C ) continue;
            map[uy][ux] = '.';
        }
    }

    static public void setBomb(){
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(map[i][j] == '.'){
                    map[i][j] = 'O';
                    bomb[i][j] = 3;
                }
                else{
                    bomb[i][j] --;
                }
            }
        }
    }

    static public void setDefaultBomb(){
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(map[i][j] == '.') bomb[i][j] = 0;
            }
        }
    }
}