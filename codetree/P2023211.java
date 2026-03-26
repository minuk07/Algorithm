import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int l,n,q;

    static int[][] map;
    static int[][] knightMap;
    static Knight[] knights;

    static boolean[] visited;
    static boolean[] moveVisited;


    static int[] dy = {-1, 0, 1, 0}; //위, 오, 아, 왼
    static int[] dx = {0, 1, 0, -1};

    static class Knight{
        int r,c,h,w,k;
        int totalDamage;

        Knight(int r, int c, int h, int w, int k){
            this.r = r; this.c = c; this.h = h; this.w = w; this.k = k;
            this.totalDamage = 0;
        }
    }

    static boolean inRange(int y, int x){
        return (y>=0 && x>=0 && y<l && x<l);
    }

    static void printList(int[][] arr){
        for(int i=0; i<l; i++){
            for(int j=0; j<l; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static boolean canMove(int idx, int dir){

        if(visited[idx]) return true;
        visited[idx] = true;

        Knight cur = knights[idx];

        for(int i=0; i<cur.h; i++){
            for(int j=0; j<cur.w; j++){
                int ddy = cur.r + i + dy[dir];
                int ddx = cur.c + j + dx[dir];

                if(!inRange(ddy, ddx)) return false;

                if(map[ddy][ddx] == 2) return false;

                int nearIdx = knightMap[ddy][ddx];
                if(nearIdx != 0 && nearIdx != idx ){

                    if(!canMove(nearIdx, dir)) return false;
                }
            }
        }

        return true;
    }

    static void doMove(int command, int idx, int dir){

        if(moveVisited[idx]) return;
        moveVisited[idx] = true;
        Knight cur = knights[idx];

        for(int i=0; i<cur.h; i++){
            for(int j=0; j<cur.w; j++){
                int ddy = cur.r + i + dy[dir];
                int ddx = cur.c + j + dx[dir];

                int nearIdx = knightMap[ddy][ddx];

                if(knightMap[ddy][ddx] != 0 && knightMap[ddy][ddx] != idx){
                    doMove(command, nearIdx, dir);
                }
            }
        }

        for(int i=cur.r; i<cur.r + cur.h; i++){
            for(int j=cur.c; j<cur.c + cur.w; j++){
                knightMap[i][j] = 0;
            }
        }

        cur.r += dy[dir];
        cur.c += dx[dir];

        int damage = 0;

        for(int i=0; i<cur.h; i++){
            for(int j=0; j<cur.w; j++){
                int ddy = cur.r + i;
                int ddx = cur.c + j;

                if(command != idx && map[ddy][ddx] == 1){
                    damage++;
                }

                knightMap[ddy][ddx] = idx;
            }
        }

        cur.k -= damage;
        cur.totalDamage += damage;
    }

    static void move(int idx, int dir){
        Knight cur = knights[idx];

        if(cur.k <= 0) return;

        visited = new boolean[n+1];

        if(!canMove(idx, dir)) return;

        moveVisited = new boolean[n+1];
        doMove(idx, idx, dir);
    }

    static void disappear(Knight cur){
        for(int i=cur.r; i<cur.r + cur.h; i++){
            for(int j=cur.c; j<cur.c + cur.w; j++){
                knightMap[i][j] = 0;
            }
        }
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        l = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        map = new int[l][l];
        knightMap = new int[l][l];
        knights = new Knight[n+1];

        for(int i=0; i<l; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<l; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // System.out.println("map:");
        // printList(map);

        for(int num=1; num<=n; num++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            knights[num] = new Knight(r, c, h, w, k);

            for(int i=r; i<r+h; i++){
                for(int j=c; j<c+w; j++){
                    knightMap[i][j] = num;
                }
            }
        }

        // printList(knightMap);

        for(int i=0; i<q; i++){
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            move(idx, dir);

            for(int j=1; j<=n; j++){
                if(knights[j].k <= 0){
                    disappear(knights[j]);
                }
            }

            // System.out.println("#"+ (i+1));
            // printList(knightMap);
        }

        long answer = 0;

        for(int i=1; i<=n; i++){
            if(knights[i].k <= 0) continue;
            answer += knights[i].totalDamage;
        }

        System.out.println(answer);
    }
}