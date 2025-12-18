import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int n;
    static int[][] map;
    static int[][][] dp;
    
    public static void solve(){
        for(int i=1; i<n; i++){
            for(int j=2; j<n; j++){
                if(map[i][j] == 1) continue;
                dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][2];
                dp[i][j][1] = dp[i-1][j][1] + dp[i-1][j][2];

                if(map[i-1][j]==1 || map[i][j-1] == 1) continue;
                dp[i][j][2] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
            }
        }   
    }
    
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        dp = new int[n][n][3];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=1; i<n; i++){
            if(map[0][i] == 1) break;
            dp[0][i][0] = 1;
            
        }

        solve();

        System.out.print(dp[n-1][n-1][0] + dp[n-1][n-1][1] + dp[n-1][n-1][2]);
    }
}