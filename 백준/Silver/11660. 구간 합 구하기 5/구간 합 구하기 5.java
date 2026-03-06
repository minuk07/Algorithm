import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int n,m;
    static int x1,y1,x2,y2;
    static int[][] arr;
    static long[][] dp;

    static void printList(long[][] a){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][n];
        dp = new long[n][n];
        
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for(int i=0; i<n; i++){
            int tmp = 0;
            for(int j=0; j<n; j++){
                tmp += arr[i][j];
                dp[i][j] = tmp;
            }
        }

        //printList(dp);

        for(int tc=0; tc<m; tc++){
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken())-1;
            y1 = Integer.parseInt(st.nextToken())-1;
            x2 = Integer.parseInt(st.nextToken())-1;
            y2 = Integer.parseInt(st.nextToken())-1;
            
            long sum = 0;
            for(int i=x1; i<=x2; i++){
                if(y1 == 0){
                    sum += dp[i][y2];
                }
                else{
                    sum += (dp[i][y2] - dp[i][y1-1]);
                }
            }

            System.out.println(sum);
        }
    }
}