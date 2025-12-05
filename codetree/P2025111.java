package org.java;


import java.util.*;
import java.io.*;

public class P2025111 {

    static int n;
    static int t;
    static int[][] F;
    static int[][] B;

    static boolean[][] visited;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static List<int[]> list;

    static StringBuilder sb ;

    public static void printList(int[][] arr){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean inRange(int y, int x){
        return (y>=0 && x>=0 && x<n && y<n);
    }

    public static int getCount(int y, int x){
        if(F[y][x] == 4 || F[y][x] == 2 || F[y][x] == 1) return 1;
        else if(F[y][x] == 6 || F[y][x] == 5 || F[y][x] == 3) return 2;
        else return 3;
    }

    public static void group(int y, int x){
        Queue<int[]> q = new LinkedList<>();
        int cnt = 1;
        q.add(new int[]{y,x});
        visited[y][x] = true;

        int cy = y;
        int cx = x;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int uy = cur[0];
            int ux = cur[1];

            for(int i=0; i<4; i++){
                int ddy = uy + dy[i];
                int ddx = ux + dx[i];

                if(!inRange(ddy, ddx)) continue;
                if(F[y][x] == F[ddy][ddx] && !visited[ddy][ddx]){
                    cnt++;
                    visited[ddy][ddx] = true;
                    q.add(new int[]{ddy,ddx});

                    if(B[cy][cx] < B[ddy][ddx]){
                        cy = ddy; cx = ddx;
                    }
                    else if(B[cy][cx] == B[ddy][ddx]){
                        if(cy > ddy) {
                            cy = ddy; cx = ddx;
                        }
                        else if(cy == ddy && cx > ddx){
                            cy = ddy; cx = ddx;
                        }
                    }
                }
            }
        }

        B[cy][cx] += cnt;
        list.add(new int[]{cy, cx});
    }

    public static void prop(int r, int c){
        if(visited[r][c]) return;

        int dir = B[r][c] % 4;

        int ddy = r + dy[dir];
        int ddx = c + dx[dir];

        int x = B[r][c] - 1;
        B[r][c] = 1;

        while(inRange(ddy,ddx) && x > 0) {
            //System.out.println(ddy + "," + ddx);
            if(F[ddy][ddx] != F[r][c]) {

                int y = B[ddy][ddx];
                if (x > y) {
                    F[ddy][ddx] = F[r][c];
                    x -= (y + 1);
                    B[ddy][ddx] += 1;
                } else {
                    F[ddy][ddx] = F[ddy][ddx] | F[r][c];
                    B[ddy][ddx] += x;
                    x = 0;
                }
                visited[ddy][ddx] = true;
            }
            ddy += dy[dir];
            ddx += dx[dir];

        }
    }

    public static void calculate(){
        long[] sum = new long[8];

        for(int i=0; i<n ;i++){
            for(int j=0; j<n; j++){
                sum[F[i][j]] += B[i][j];
            }
        }

        sb.append(sum[7]).append(" ").append(sum[6]).append(" ").append(sum[5]).append(" ")
                .append(sum[3]).append(" ").append(sum[1]).append(" ").append(sum[2]).append(" ")
                .append(sum[4]).append("\n");
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        F = new int[n][n];
        B = new int[n][n];

        for(int i=0; i<n; i++){
            String s = br.readLine();
            for(int j=0; j<n; j++){
                char c = s.charAt(j);
                if(c == 'T') F[i][j] = 4;
                else if(c == 'C') F[i][j] = 2;
                else F[i][j] = 1;
            }
        }

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                B[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        sb = new StringBuilder();

        for(int tc =1; tc <= t; tc++) {

            visited = new boolean[n][n];
            list = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j]) {
                        group(i, j);
                    }
                }
            }

            list.sort((a,b) -> {
                int c1 = getCount(a[0], a[1]);
                int c2 = getCount(b[0], b[1]);

                if(c1 != c2) return Integer.compare(c1, c2);

                int b1 = B[a[0]][a[1]];
                int b2 = B[b[0]][b[1]];

                if(b1 != b2) return Integer.compare(b2, b1);

                if(a[0] != b[0]) return Integer.compare(a[0], b[0]);

                return Integer.compare(a[1], b[1]);
            });

            visited = new boolean[n][n];

            for(int[] arr : list){
                prop(arr[0], arr[1]);
            }

            calculate();

            //printList(B);
        }

        System.out.print(sb.toString());
    }
}