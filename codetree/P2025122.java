package org.codetree;

import java.util.*;
import java.io.*;

public class P2025122 {

    static int[] map = new int [20001];
    static int idx;

    public static int check(int rq){
        int l = 0;
        int r = 1000000000;

        while(l < r){
            int mid = (l+r)/2;


            int tmp = antCount(mid);

            if(tmp > rq){
                l = mid + 1;
            }
            else{
                r = mid;
            }
        }

        return l;
    }

    private static int antCount(int time){
        int cnt = 1;

        int prevIdx = 0;
        int prevX = 0;

        for(int i=1; i<=idx; i++){

            int cur = map[i];
            if(map[i] == -1) continue;

            if(prevIdx == 0){
                prevIdx = i;
                prevX = cur;
            }
            else {
                if (cur - prevX > time) {
                    prevIdx = i;
                    prevX = cur;
                    cnt++;
                }
            }

        }

        return cnt;
    }

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        StringBuilder sb = new StringBuilder();
        idx = 0;

        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int cmd = Integer.parseInt(st.nextToken());

            if(cmd == 100){
                int num = Integer.parseInt(st.nextToken());

                for(int j=0; j<num; j++){
                    int x = Integer.parseInt(st.nextToken());
                    map[++idx] = x;
                }
            }
            else if(cmd == 200){
                int p = Integer.parseInt(st.nextToken());

                map[++idx] = p;
            }
            else if(cmd == 300){
                int q = Integer.parseInt(st.nextToken());

                map[q] = -1;
            }
            else if(cmd == 400){
                int r = Integer.parseInt(st.nextToken());
                int result = check(r);

                sb.append(result).append('\n');
            }
        }

        System.out.print(sb.toString());
    }
}