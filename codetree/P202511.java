package org.codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P202511 {

    static int n;
    static int m;
    static List<Box> list;
    static int[][] map;

    public static class Box{
        int k, w, h, r, c;
        Box(int k, int w, int h, int r, int c){
            this.k=k;this.w=w;this.h=h;this.r=r;this.c=c;
        }
    }


    static boolean inRange(int y, int x){
        return (y>=0 && x >= 0 && y<n && x<n);
    }

    public static void move(Box b){
        int curR = b.r;
        boolean flag = true;
        while(curR < n){
            for(int i=1; i<=b.w; i++){
                if(!inRange(curR+b.h,b.c+i-1)) {
                    flag = false;
                    break;
                }
                if(map[curR+b.h][b.c+i-1] > 0) {
                    flag = false;
                    break;
                }
            }
            if(!flag) break;
            curR++;
        }


        if(curR == b.r) {
            for(int i=curR; i<curR+ b.h; i++){
                for(int j=b.c; j<b.c + b.w; j++){
                    map[i][j] = b.k;
                }
            }
        }
        else{
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    if(map[i][j] == b.k) map[i][j] = 0;
                }
            }

            for(int i=curR; i<curR+ b.h; i++){
                for(int j=b.c; j<b.c + b.w; j++){
                    map[i][j] = b.k;
                }
            }

            b.r = curR;
        }
    }

    public static boolean isLeftRemove(Box b){
        boolean flag = true;

        int curC = b.c;

        while(curC>=0){
            for(int i=1; i<=b.h; i++){
                if(map[b.r+i-1][curC] > 0 && map[b.r+i-1][curC] != b.k) {
                    return false;
                }
            }
            curC--;
        }

        return flag;
    }

    public static boolean isRightRemove(Box b){
        boolean flag = true;

        int curC = b.c;

        while(curC + b.w - 1<n){
            for(int i=1; i<=b.h; i++){
                if(map[b.r+i-1][curC + b.w -1] > 0 && map[b.r+i-1][curC + b.w -1] != b.k) {
                    return false;
                }
            }
            curC++;
        }

        return flag;
    }

    public static void remove(int now){
        for(Box b : list){
            if(b.k == now) {
                list.remove(b);
                for(int i=0; i<n; i++){
                    for(int j=0; j<n; j++){
                        if(map[i][j] == now) map[i][j] = 0;
                    }
                }
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken()) - 1;

            list.add(new Box(k,w,h,0,c));
        }

        map = new int[n][n];
        StringBuilder sb = new StringBuilder();

        while(!list.isEmpty()) {

            for (Box b : list) {
                move(b);
            }

            int now = -1;
            for (Box b : list) {
                if (isLeftRemove(b)) {
                    if (now == -1 || now > b.k) {
                        now = b.k;
                    }
                }
            }

            remove(now);

            sb.append(now).append('\n');

            for (Box b : list) {
                move(b);
            }

            now = -1;
            for (Box b : list) {
                if (isRightRemove(b)) {
                    if (now == -1 || now > b.k) {
                        now = b.k;
                    }
                }
            }

            remove(now);

            sb.append(now).append('\n');

            for (Box b : list) {
                move(b);
            }
        }

        System.out.println(sb.toString());
    }
}
