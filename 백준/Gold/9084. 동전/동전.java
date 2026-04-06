import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int t;
    static int n;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        t = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=t; tc++){
            n = Integer.parseInt(br.readLine());

            int[] coin = new int[n];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++){
                coin[i] = Integer.parseInt(st.nextToken());
            }

            int total = Integer.parseInt(br.readLine());

            int[] dp = new int[total + 1];

            dp[0]= 1;
            

            for(int i=0; i<n; i++){
                for(int j=coin[i]; j<=total; j++){
                    dp[j] += dp[j - coin[i]];
                }
            }

            System.out.println(dp[total]);
        }
    }
}