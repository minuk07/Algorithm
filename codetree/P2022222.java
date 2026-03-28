import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int n, q, size;
    static int[][] map;
    static int[][] temp;
    static boolean[][] visited;
    static long answer;

    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};

    static void printList(){
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static boolean inRange(int y, int x){
        return (y>=0 && x>=0 && y<size && x<size);
    }

    static void move(int y, int x, int hSize, int dir){
        for(int i=y; i<y+hSize; i++){
            for(int j=x; j<x+hSize; j++){
                int ddy = i + dy[dir] * hSize;
                int ddx = j + dx[dir] * hSize;

                temp[ddy][ddx] = map[i][j];
            }
        }
    }

    static void rotate(int level){
        temp = new int[size][size];

        int rSize = (int)Math.pow(2, level);
        int hSize = (int)Math.pow(2, level-1);

        for(int i=0; i<size; i+=rSize){
            for(int j=0; j<size; j+=rSize){
                move(i, j, hSize, 0);
                move(i, j + hSize, hSize, 1);
                move(i + hSize, j + hSize, hSize, 2);
                move(i + hSize, j, hSize, 3);
            }
        }

        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                map[i][j] = temp[i][j];
            }
        }
    }

    static void melt(){
        temp = new int[size][size];

        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){

                if(map[i][j] == 0){
                    temp[i][j] = 0;
                    continue;
                }

                int cnt = 0;

                for(int d=0; d<4; d++){
                    int ddy = i + dy[d];
                    int ddx = j + dx[d];

                    if(!inRange(ddy, ddx)) continue;
                    if(map[ddy][ddx] == 0) continue;

                    cnt++;
                }

                if(cnt < 3) {
                    temp[i][j] = map[i][j] - 1;
                }
                else{
                    temp[i][j] = map[i][j];
                }
            }
        }

        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                map[i][j] = temp[i][j];
            }
        }

    }

    static int bfs(int y, int x){

        Queue<int[]> q = new LinkedList<>();
        visited[y][x] = true;
        int cnt = 1;

        q.add(new int[]{y, x});

        while(!q.isEmpty()){
            int[] cur = q.poll();

            //answer += map[cur[0]][cur[1]];

            for(int i=0; i<4; i++){
                int ddy = cur[0] + dy[i];
                int ddx = cur[1] + dx[i];

                if(!inRange(ddy, ddx)) continue;
                if(map[ddy][ddx] == 0) continue;
                if(visited[ddy][ddx]) continue;

                visited[ddy][ddx] = true;
                cnt++;
                q.add(new int[]{ddy, ddx});
            }
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        size = (int)Math.pow(2, n);
        map = new int[size][size];

        for(int i=0; i<size; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<size; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //printList();

        st = new StringTokenizer(br.readLine());

        for(int i=0; i<q; i++){
            int level = Integer.parseInt(st.nextToken());

            if(level > 0) rotate(level);

            melt();
        }

        visited = new boolean[size][size];

        int group = 0;
        answer = 0;

        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                answer += map[i][j];
            }
        }

        //printList();

        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                if(!visited[i][j] && map[i][j] > 0){
                    group = Math.max(group, bfs(i, j));
                }
            }
        }

        System.out.print(answer + "\n" + group);
    }
}