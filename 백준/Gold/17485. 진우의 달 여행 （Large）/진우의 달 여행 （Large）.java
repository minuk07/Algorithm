import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int n, m;
    static int[][] map;
    static final int INF = 10000000;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        int[][][] dp = new int[n][m][3];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
        
                dp[i][j][0] = INF;
                dp[i][j][1] = INF;
                dp[i][j][2] = INF;
        
            }
        }

        for(int j=0; j<m; j++){
            for(int d=0; d<3; d++){
                dp[0][j][d] = map[0][j];
            }
        }
        
        for(int i=1; i<n; i++){
            for(int j=0; j<m; j++){

                if(j == 0){
                    dp[i][j][1] = dp[i-1][j][2] + map[i][j];
                    dp[i][j][2] = Math.min(dp[i-1][j+1][0], dp[i-1][j+1][1]) + map[i][j];
                }
                else if(j == m-1){
                    dp[i][j][1] = dp[i-1][j][0] + map[i][j];
                    dp[i][j][0] = Math.min(dp[i-1][j-1][1],dp[i-1][j-1][2]) + map[i][j];
                }
                else{
                    dp[i][j][0] = Math.min(dp[i-1][j-1][1], dp[i-1][j-1][2]) + map[i][j];
                    dp[i][j][1] = Math.min(dp[i-1][j][0], dp[i-1][j][2]) + map[i][j];
                    dp[i][j][2] = Math.min(dp[i-1][j+1][0],dp[i-1][j+1][1]) + map[i][j];
                }
            
            }
        }

        int result = Integer.MAX_VALUE;

        for(int i=0; i<m; i++){
            for(int d=0; d<3; d++){
                result = Math.min(dp[n-1][i][d], result);
            }
        }

        System.out.println(result);
    }
}