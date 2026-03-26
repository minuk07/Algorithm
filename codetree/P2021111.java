import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int[] dy = {0, 1, 0, -1}; //오 아 왼 위
    static int[] dx = {1, 0, -1, 0};

    static int n,m;
    static int[][] map;

    static class Dice{
        int r, c, dir, up, right, front;

        Dice(int up, int right, int front){
            this.r = 0; this.c = 0; this.dir = 0;
            this.up = up; this.right= right; this.front = front;
        }
    }

    static int getDir(Dice dice){
        int y = dice.r; int x = dice.c;
        int num = 7-dice.up;

        if(map[y][x] < num ) return 1;
        else if(map[y][x] > num ) return -1;
        else return 0;
    }

    static void roll(Dice dice){
        int up = dice.up; int right = dice.right; int front = dice.front;
        int dir = dice.dir;

        if(dir == 0){
            dice.up = 7-right; dice.right = up; dice.front = front;
        }
        else if(dir == 1){
            dice.up = 7-front; dice.right = right; dice.front = up;
        }
        else if(dir == 2){
            dice.up = right; dice.right = 7-up; dice.front = front;
        }
        else{
            dice.up = front; dice.right = right; dice.front = 7-up;
        }
    }

    static boolean inRange(int y, int x){
        return (y>=0 && x>=0 && y<n && x<n);
    }

    static int getScore(int y, int x){
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        int num = map[y][x];
        int score = num;

        q.add(new int[]{y, x});
        visited[y][x] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();

            for(int i=0; i<4; i++){
                int ddy = cur[0] + dy[i];
                int ddx = cur[1] + dx[i];

                if(!inRange(ddy, ddx)) continue;
                if(visited[ddy][ddx]) continue;
                if(map[ddy][ddx] != num) continue;

                visited[ddy][ddx] = true;
                score += num;
                q.add(new int[]{ddy, ddx});
            }
        }

        return score;
    }

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        long answer = 0;

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Dice dice = new Dice(1, 3, 2);

        for(int i=0; i<m; i++){
            int ddy = dice.r + dy[dice.dir];
            int ddx = dice.c + dx[dice.dir];

            if(!inRange(ddy, ddx)){
                dice.dir = (dice.dir + 2) % 4;
                ddy = dice.r + dy[dice.dir];
                ddx = dice.c + dx[dice.dir];
            }

            dice.r = ddy; dice.c = ddx;

            roll(dice);

            answer += getScore(ddy, ddx);

            dice.dir = (dice.dir + getDir(dice) + 4) % 4;
        }

        System.out.println(answer);
    }
}