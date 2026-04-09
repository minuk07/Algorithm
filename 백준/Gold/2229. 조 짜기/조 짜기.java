import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] score = new int[n+1];
        int[] dp = new int[n+1];

        for(int i=1; i<=n; i++){
            score[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1; i<=n; i++){
            int max = score[i];
            int min = score[i];

            for(int j=i; j>=1; j--){
                max = Math.max(max, score[j]);
                min = Math.min(min, score[j]);

                dp[i] = Math.max(dp[i], max - min + dp[j-1]);
            }
        }

        System.out.println(dp[n]);
    }
}