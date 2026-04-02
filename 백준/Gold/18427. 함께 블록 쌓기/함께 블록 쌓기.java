import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int n, m, h;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        int[][] dp = new int[n+1][h+1];

        for(int i=0; i<n+1; i++){
            dp[i][0] = 1;
        }     

        for(int i=1; i<=n; i++){
            String[] blocks = br.readLine().split(" ");

            for(int j=1; j<=h; j++){

                dp[i][j] = dp[i-1][j];

                for(String b : blocks){
                    int block = Integer.parseInt(b);

                    if(j>=block){
                        dp[i][j] = (dp[i][j] + dp[i-1][j-block]) % 10007;
                     }
                }
            }
        }

        System.out.println(dp[n][h]);
    }
}