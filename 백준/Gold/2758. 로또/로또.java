import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int tc, n, m;

    public static void main(String[] args)throws IOException {
    
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        tc = Integer.parseInt(br.readLine());

        for(int t=0; t<tc; t++){
        
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
    
            long[][] dp = new long[n+1][m+1];
        
            for(int i=1; i<=m; i++){
                dp[1][i] = i;
            }
    
            for(int i=2; i<=n; i++){
                for(int j=(int)Math.pow(2, i-1); j<=m; j++){
                    dp[i][j] = dp[i-1][j/2] + dp[i][j-1];
                }
            }
            
    
            sb.append(dp[n][m]).append("\n");
        }

        System.out.println(sb);
    }
}