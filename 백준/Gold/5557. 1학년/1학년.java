import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int n;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());
        int[] num = new int[n];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for(int i=0; i<n; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }

        long [][] dp = new long[n-1][21];

        dp[0][num[0]] = 1;
        
        for(int i=1; i<n-1; i++){
            for(int j=0; j<=20; j++){
                if(j - num[i] >= 0) dp[i][j] += dp[i-1][j-num[i]];
                if(j + num[i] <= 20) dp[i][j] += dp[i-1][j+num[i]];
            }
        }

        System.out.println(dp[n-2][num[n-1]]);
    }
}