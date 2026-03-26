import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int n;
    static int[][] map;
    static int[][] groupMap;
    static List<Art> list;

    static long answer;

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static class Art{
        int idx, score;

        Art(int idx, int score){
            this.idx = idx; this.score = score;
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

    static void rotate90(){

        int[][] temp = new int[n/2][n/2];
        int size = n/2;

        //좌측상단
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                temp[j][size-1-i] = map[i][j];
            }
        }

        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                map[i][j] = temp[i][j];
            }
        }

        //우측상단
        temp = new int[size][size];

        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                temp[j][size-1-i] = map[i][j+size+1];
            }
        }

        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                map[i][j+size+1] = temp[i][j];
            }
        }

        ////좌측하단
        temp = new int[size][size];

        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                temp[j][size-1-i] = map[i+size+1][j];
            }
        }

        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                map[i+size+1][j] = temp[i][j];
            }
        }

        //우측하단
        temp = new int[size][size];

        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                temp[j][size-1-i] = map[i+size+1][j+size+1];
            }
        }

        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                map[i+size+1][j+size+1] = temp[i][j];
            }
        }
    }

    static void rotate270(){

        int[][] temp = new int[n][n];

        for(int i=0; i<n; i++){
            temp[n-1-(n/2)][i] = map[i][n/2];
            temp[n-1-i][n/2] = map[n/2][i];
        }

        for(int i=0; i<n; i++){
            map[i][n/2] = temp[i][n/2];
            map[n/2][i] = temp[n/2][i];
        }
    }

    static int bfs(int y, int x, int num, int groupNum){
        Queue<int[]> q = new LinkedList<>();
        groupMap[y][x] = groupNum;

        int cnt = 1;
        q.add(new int[]{y, x});

        while(!q.isEmpty()){
            int[] cur = q.poll();

            for(int i=0; i<4; i++){
                int ddy = cur[0] + dy[i];
                int ddx = cur[1] + dx[i];

                if(!inRange(ddy, ddx)) continue;
                if(map[ddy][ddx] != num) continue;
                if(groupMap[ddy][ddx] != 0) continue;

                groupMap[ddy][ddx] = groupNum;
                cnt++;
                q.add(new int[]{ddy, ddx});
            }
        }

        return cnt;
    }

    static int getScore(){

        int sum = 0;


        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                for(int d=0; d<4; d++){
                    int ddy = i + dy[d];
                    int ddx = j + dx[d];

                    if(!inRange(ddy, ddx)) continue;
                    if(groupMap[i][j] != groupMap[ddy][ddx]){
                        int a = groupMap[i][j];
                        int b = groupMap[ddy][ddx];

                        int sumA = list.get(a-1).score;
                        int sumB = list.get(b-1).score;

                        sum += ((sumA + sumB) * list.get(a-1).idx * list.get(b-1).idx);
                    }
                }
            }
        }

        return sum/2;
    }

    static void doGetScore(){
        int groupNum = 1;
        groupMap = new int[n][n];
        list = new ArrayList<>();

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(groupMap[i][j] == 0){
                    int cnt = bfs(i, j, map[i][j], groupNum);
                    list.add(new Art(map[i][j], cnt));
                    groupNum++;
                }
            }
        }

        answer += getScore();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = 0;
        doGetScore();

        for(int i=0; i<3; i++){
            rotate270();
            rotate90();
            doGetScore();
        }

        System.out.print(answer);
    }
}