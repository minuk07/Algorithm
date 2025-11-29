package org.codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P202521 {

    static int n;
    static int l;
    static int k;
    static int[][] map;
    static int[][] B;
    static List<Robot> list = new ArrayList<>();

    static int[] dy = {0, 0, -1, 1}; //우 , 좌, 상, 하
    static int[] dx = {1, -1, 0, 0};

    static int[] cy = {0, -1, 0, 1, 0};
    static int[] cx = {-1, 0, 1, 0, 0};

    static class Robot{
        int id, y, x;

        Robot(int id, int y, int x){
            this.id = id;
            this.y = y;
            this.x = x;
        }
    }

    public static boolean inRange(int y, int x){
        return (y>=0 && x >=0 && y<n && x<n);
    }

    public static void move(Robot rb){
        int y = rb.y;
        int x = rb.x;

        if(map[y][x] > 0) return;

        int bestDist = -1;
        int bestY = -1;
        int bestX = -1;

        ArrayDeque<int[]> q = new ArrayDeque<>();
        int[][] dist = new int[n][n];

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                dist[i][j] = -1;
            }
        }

        q.add(new int[]{y, x});
        dist[y][x] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int uy = cur[0];
            int ux = cur[1];

            if(bestDist != -1 && dist[uy][ux] > bestDist) break;

            for(int i=0; i<4; i++){
                int ddy = uy + dy[i];
                int ddx = ux + dx[i];

                if(!inRange(ddy, ddx)) continue;
                if(dist[ddy][ddx] != -1) continue;
                if(map[ddy][ddx] == -1) continue;
                if(B[ddy][ddx] >= 0 ) continue;

                dist[ddy][ddx] = dist[uy][ux] + 1;
                q.add(new int[]{ddy, ddx});

                if(map[ddy][ddx] > 0){
                    if(bestDist == -1 || bestDist == dist[ddy][ddx]){
                        if(bestDist == -1){
                            bestDist = dist[ddy][ddx];
                            bestY = ddy;
                            bestX = ddx;
                        }
                        else{
                            if(ddy < bestY){
                                bestY = ddy;
                                bestX = ddx;
                            }
                            else if(bestY == ddy && ddx < bestX){
                                bestY = ddy;
                                bestX = ddx;
                            }
                        }
                    }
                }
            }
        }

        if(bestDist != -1){
            B[bestY][bestX] = rb.id;
            B[rb.y][rb.x] = -1;
            rb.y = bestY;
            rb.x = bestX;
        }

    }

    public static void clean(Robot rb){
        // System.out.println("rb.y=" + rb.y + ", rb.x=" + rb.x);
        int bestNoDir = -1;
        int bestSum = 0;

        for(int i=0; i<4; i++){
            int tmp = 0;
            for(int c=0; c<5; c++){
                int ddy = rb.y + cy[c];
                int ddx = rb.x + cx[c];

                if(!inRange(ddy, ddx)) continue;
                if(c < 4 && c==i) continue;
                if(map[ddy][ddx] > 0){
                    tmp += Math.min(20, map[ddy][ddx]);
                }
            }

            if(bestNoDir == -1 || tmp > bestSum){
                bestNoDir = i;
                bestSum = tmp;
            }
        }

        if(bestSum > 0 && bestNoDir != -1) {
            for (int i = 0; i < 5; i++) {
                int ddy = rb.y + cy[i];
                int ddx = rb.x + cx[i];

                if (!inRange(ddy, ddx)) continue;
                if (i < 4 && i == bestNoDir) continue;
                if(map[ddy][ddx] > 0 ) map[ddy][ddx] =  Math.max(0,map[ddy][ddx] - 20);
            }
        }

//        for(int i=0; i<n; i++){
//            for(int j=0; j<n; j++){
//                System.out.print(map[i][j]);
//            }
//            System.out.println();
//        }
//        System.out.println();
    }

    public static void increase(){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(map[i][j] > 0) map[i][j] = map[i][j] + 5;
            }
        }
    }

    public static void spread(){
        int[][] temp = new int[n][n];

        for(int i =0; i<n; i++){
            for(int j=0; j<n; j++){
                if(map[i][j] == 0){
                    int sum = 0;
                    for(int s=0; s<4; s++){
                        int ddy = i + dy[s];
                        int ddx = j + dx[s];

                        if(!inRange(ddy,ddx)) continue;
                        if(map[ddy][ddx] > 0){
                            sum += map[ddy][ddx];
                        }
                    }
                    temp[i][j] = sum/10;
                }
            }
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(temp[i][j] > 0 && map[i][j] == 0){
                    map[i][j] = temp[i][j];
                }
            }
        }

//        for(int i=0; i<n; i++){
//            for(int j=0; j<n; j++){
//                System.out.print(map[i][j]);
//            }
//            System.out.println();
//        }
//        System.out.println();
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        B = new int[n][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                B[i][j] = -1;
            }
        }

        for(int i=0; i<k; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            list.add(new Robot(i,r,c));
            B[r][c] = i;
        }

//        for(int i=0; i<n; i++){
//            for(int j=0; j<n; j++){
//                System.out.print(B[i][j]);
//            }
//            System.out.println();
//        }
//        System.out.println();

        StringBuilder sb = new StringBuilder();

        for(int i=0; i<l; i++){
            for(Robot rb : list){
                move(rb);
            }

            for(Robot rb : list){
                clean(rb);
            }

            increase();

            spread();

            long result = 0;

            for(int j=0; j<n; j++){
                for(int k=0; k<n; k++){
                    if(map[j][k] > 0) result += map[j][k];
                }
            }

            sb.append(result).append('\n');

            if(result == 0) break;

        }

        System.out.print(sb.toString());
    }

}