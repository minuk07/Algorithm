import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int t;
    static int n;
    static int[][] sticker;
    static int[][] dp;

    static void printList(int[][] arr){
        for(int i=0; i<2; i++){
            for(int j=0; j<n; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        t = Integer.parseInt(st.nextToken());

        for(int tc=0; tc<t; tc++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            sticker = new int[2][n];
            dp = new int[2][n];

            for(int i=0; i<2; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<n; j++){
                    sticker[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            if(n == 1){
                System.out.println(Math.max(sticker[0][0], sticker[1][0]));
                continue;
            }

            dp[0][0] = sticker[0][0]; dp[1][0] = sticker[1][0];
            dp[0][1] = sticker[1][0] + sticker[0][1];
            dp[1][1] = sticker[1][1] + sticker[0][0];

            for(int i=2; i<n; i++){
                dp[0][i] = Math.max(dp[1][i-1], dp[1][i-2]) + sticker[0][i];
                dp[1][i] = Math.max(dp[0][i-1], dp[0][i-2]) + sticker[1][i];
            }
            
            System.out.println(Math.max(dp[0][n-1], dp[1][n-1]));
        }
    }
}