package org.java;

import java.util.*;
import java.io.*;

public class P2023121 {
    static int n;
    static int m;
    static int k;
    static int[][] map;
    static List<int[]> list;
    static int[] exit;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static int result;

    public static void printList(int[][] arr){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printList(List<int[]> list){
        for(int i=0; i<list.size(); i++){
            System.out.println("{" + list.get(i)[0] + "," + list.get(i)[1]+"] dist = " + list.get(i)[2]);
        }
    }

    public static boolean inRange(int y, int x){
        return (y >= 0 && x >= 0 && y < n && x < n);
    }

    public static void move(int[] arr){
        int y = arr[0];
        int x = arr[1];
        int exitY = exit[0];
        int exitX = exit[1];
        int curDist = Math.abs(exitY - y) + Math.abs(exitX - x);
        //System.out.println("현재: "+y+","+x);

        for(int i=0; i<4; i++){
            int ddy = y + dy[i];
            int ddx = x + dx[i];

            if(!inRange(ddy, ddx)) continue;
            if(map[ddy][ddx] > 0) continue;

            int newDist = Math.abs(ddy - exitY) + Math.abs(ddx - exitX);
            if(curDist <= newDist) {
                //System.out.println("이동거리 안 짧아짐");
                continue;
            }

            arr[0] = ddy;
            arr[1] = ddx;
            arr[2] += 1;
            result++;
//            System.out.println("이동: "+ddy+","+ddx);
//            System.out.println("탈출: "+exit[0]+","+exit[1]);

            if(ddy == exitY && ddx == exitX){
                arr[3] = -1;
            }

            break;
        }

    }

    public static void getSquare(){
        int bestY = n;
        int bestX = n;
        int bestLen = n+1;

       // printList(list);

        for(int i=0; i<list.size(); i++){
            if(list.get(i)[3] == -1) continue;
            int py = list.get(i)[0];
            int px = list.get(i)[1];

            int minY = Math.min(py, exit[0]);
            int minX = Math.min(px, exit[1]);
            int maxY = Math.max(py, exit[0]);
            int maxX = Math.max(px, exit[1]);

            int curLen = Math.max(maxY - minY + 1, maxX - minX + 1);

            for(int r=maxY-curLen+1 ; r <= minY; r++){
                for(int c=maxX-curLen+1; c<=minX; c++){
                    if(r<0 || c<0 || r+curLen > n || c+curLen>n ) continue;

                    if(curLen < bestLen || (curLen == bestLen && (r < bestY || (r == bestY && c <bestX)))){
                        bestLen = curLen; bestY = r; bestX = c;
                    }
                }
            }

        }

        //System.out.println(bestY + "," + bestX + "," +bestLen);

        if(bestLen == n + 1) return;

        rotate(bestY, bestX, bestLen);
    }

    public static void rotate(int y, int x, int len){
        int[][] temp = new int[len][len];

        for(int i=0; i<len; i++){
            for(int j=0; j<len; j++){
                int originY = i + y;
                int originX = j + x;
                temp[j][len-1-i] = map[originY][originX];
            }
        }

        for(int i=0; i<len; i++){
            for(int j=0; j<len; j++){
                int v = temp[i][j];
                if(v > 0) v--;        // 회전된 벽의 내구도 감소
                map[y + i][x + j] = v;
            }
        }

        int ey = exit[0] - y;
        int ex = exit[1] - x;

        exit[0] = ex + y;
        exit[1] = (len - 1 - ey) + x;

        for(int[] arr : list){
            if(arr[3] == -1 ) continue;
            int ddy = arr[0]- y; int ddx = arr[1] - x;

            if(ddy>=0 && ddx >=0 && ddy<len && ddx<len){
                arr[0] = ddx + y;
                arr[1] = (len - 1 - ddy) + x;
            }
        }

    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        list = new ArrayList<>();

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=1; i<=m; i++){
            st = new  StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) -1;
            int c = Integer.parseInt(st.nextToken()) -1;

            list.add(new int[]{r, c, 0, 1});
        }

        st = new StringTokenizer(br.readLine());
        int er = Integer.parseInt(st.nextToken()) - 1;
        int ec = Integer.parseInt(st.nextToken()) - 1;

        exit = new int[]{er, ec};

        result = 0;

        for(int tc=1; tc<=k; tc++){

            //System.out.println("#"+tc);

            //printList(map);

            for(int[] arr: list){
                if(arr[3] == -1) continue;
                move(arr);
            }
//
//            System.out.println("move");
//            printList(map);

            getSquare();

            for(int[] arr : list){
                if(arr[3] == -1) continue;
                if(arr[3] == 0){
                    arr[3] = 1;
                }
            }
        }

//        for(int[] arr: list){
//            if(arr[3] == -1) continue;
//            result += arr[2];
//        }
        StringBuilder sb = new StringBuilder();
        sb.append(result).append("\n").append(exit[0]+1).append(" ").append(exit[1]+1);

        System.out.print(sb.toString());

    }
}