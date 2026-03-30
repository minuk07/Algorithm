import java.util.*;
import java.lang.*;
import java.io.*;


class Main {

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static int n, m, k, c;
    static int[][] map;
    static int[][] cntMap;
    static int[][] stayMap;

    static long answer;

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

    static void grow(){
        int[][] temp = new int[n][n];
        boolean flag = false;

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(map[i][j] == -1){
                    temp[i][j] = -1;
                    continue;
                }
                if(map[i][j] == 0) continue;

                int cnt = 0;

                for(int d=0; d<4; d++){
                    int ddy = i + dy[d];
                    int ddx = j + dx[d];

                    if(!inRange(ddy, ddx)) continue;
                    if(map[ddy][ddx] <= 0) continue;

                    cnt++;
                }
                temp[i][j] = map[i][j] + cnt;
            }
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                map[i][j] = temp[i][j];
            }
        }
    }

    static void setCntMap(){

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(map[i][j] <= 0) continue;

                int cnt = 0;

                for(int d=0; d<4; d++){
                    int ddy = i + dy[d];
                    int ddx = j + dx[d];

                    if(!inRange(ddy, ddx)) continue;
                    if(map[ddy][ddx] == 0){
                        cnt++;
                    }
                }

                cntMap[i][j] = cnt;
            }
        }
    }

    static void spread(){

        int[][] temp = new int[n][n];

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){

                if(map[i][j] <= 0 || cntMap[i][j] == 0) continue;

                if(cntMap[i][j] == 0){
                    System.out.println(map[i][j]+"#### y: "+i+" x: "+j);
                }
                int tree = map[i][j] / cntMap[i][j];

                for(int d=0; d<4; d++){
                    int ddy = i + dy[d];
                    int ddx = j + dx[d];

                    if(!inRange(ddy, ddx)) continue;
                    if(stayMap[ddy][ddx] > 0) continue;
                    if(map[ddy][ddx] == 0){
                        temp[ddy][ddx] += tree;
                    }
                }
            }
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(map[i][j] == -1 || map[i][j] > 0) continue;
                if(temp[i][j] > 0) map[i][j] = temp[i][j];
            }
        }
    }

    static int[] getDestroy(){
        int max = 0;
        int[] result = {-1, -1};

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){

                if(map[i][j] <= 0) continue;
                if(stayMap[i][j] > 0) stayMap[i][j]--;

                int temp = map[i][j];
                for(int d=1; d<=k; d++){
                    if(!inRange(i-d, j-d)) continue;
                    if(map[i-d][j-d] <= 0) break;
                    temp += map[i-d][j-d];
                }
                for(int d=1; d<=k; d++){
                    if(!inRange(i+d, j-d)) continue;
                    if(map[i+d][j-d] <= 0) break;
                    temp += map[i+d][j-d];
                }
                for(int d=1; d<=k; d++){
                    if(!inRange(i+d, j+d)) continue;
                    if(map[i+d][j+d] <= 0) break;
                    temp += map[i+d][j+d];
                }
                for(int d=1; d<=k; d++){
                    if(!inRange(i-d, j+d)) continue;
                    if(map[i-d][j+d] <= 0) break;
                    temp += map[i-d][j+d];
                }

                if(max < temp){

                    max = temp;
                    result[0] = i;
                    result[1] = j;
                }
            }
        }

        return result;
    }

    static void spray(int y, int x){

        stayMap[y][x] = c;
        answer += map[y][x];
        map[y][x] = 0;

        for(int d=1; d<=k; d++){
            int ddy = y-d; int ddx = x-d;
            if(!inRange(ddy, ddx)) continue;
            if(map[ddy][ddx] == -1) break;
            stayMap[ddy][ddx] = c;
            if(map[ddy][ddx] == 0) break;
            answer += map[ddy][ddx];
            map[ddy][ddx] = 0;
        }
        for(int d=1; d<=k; d++){
            int ddy = y+d; int ddx = x-d;
            if(!inRange(ddy, ddx)) continue;
            if(map[ddy][ddx] == -1) break;
            stayMap[ddy][ddx] = c;
            if(map[ddy][ddx] == 0) break;
            answer += map[ddy][ddx];
            map[ddy][ddx] = 0;
        }
        for(int d=1; d<=k; d++){
            int ddy = y-d; int ddx = x+d;
            if(!inRange(ddy, ddx)) continue;
            if(map[ddy][ddx] == -1) break;
            stayMap[ddy][ddx] = c;
            if(map[ddy][ddx] == 0) break;
            answer += map[ddy][ddx];
            map[ddy][ddx] = 0;
        }
        for(int d=1; d<=k; d++){
            int ddy = y+d; int ddx = x+d;
            if(!inRange(ddy, ddx)) continue;
            if(map[ddy][ddx] == -1) break;
            stayMap[ddy][ddx] = c;
            if(map[ddy][ddx] == 0) break;
            answer += map[ddy][ddx];
            map[ddy][ddx] = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        stayMap = new int[n][n];
        answer = 0;

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<m; i++){
            // System.out.println("#"+(i+1));
            cntMap = new int[n][n];

            grow();
            setCntMap();
            spread();

            // System.out.println("####spread#####");
            // printList(map);

            int[] cur = getDestroy();
            int y = cur[0]; int x = cur[1];
            // System.out.println("y: "+ y + " x: "+ x);

            if(y != -1) spray(y, x);

            // System.out.println("####spray#####");
            // printList(map);
        }

        System.out.print(answer);
    }
}