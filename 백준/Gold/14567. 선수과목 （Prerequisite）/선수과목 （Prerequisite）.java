import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int n, m;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] pre = new int[m][2];

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            pre[i][0] = Integer.parseInt(st.nextToken());
            pre[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(pre, (a,b) -> a[0] - b[0]);

        // for(int i=0; i<m; i++){
        //     System.out.println(pre[i][0] + " " + pre[i][1]);
        // }

        int[] dp = new int[n+1];
        
        for(int i=1; i<=n; i++){
            dp[i] = 1;
        }

        for(int i=0; i<m; i++){
            int a = pre[i][0];
            int b = pre[i][1];

            dp[b] = Math.max(dp[a] + 1, dp[b]);
            
        }

        for(int i=1; i<=n; i++){
            System.out.print(dp[i] + " ");
        }
    }
}