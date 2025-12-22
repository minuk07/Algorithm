
import java.util.*;
import java.io.*;

public class Main {

    static final int INF = 1000000000;

    static int n;
    static int m;
    static int[][] dp;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());

        dp = new int[n+1][n+1];

        for(int i=0; i<n+1; i++){
            for(int j=0; j<n+1; j++){
                if(i == j) dp[i][j] = 0;
                else dp[i][j] = INF;
            }
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            dp[a][b] = Math.min(dp[a][b], c);
        }

        for(int k=1; k<n+1; k++){
            for(int i=1; i<n+1; i++){
                for(int j=1; j<n+1; j++){
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }

        for(int i=1; i<n+1; i++){
            for(int j=1; j<n+1; j++){
                if(dp[i][j] == INF) {
                    System.out.print("0 ");
                }
                else{
                    System.out.print(dp[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}