import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static void printList(int[][] arr,int n){
        for(int i=0; i<2; i++){
            for(int j=0; j<=n; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int dp[][] = new int[2][str.length()+1];
        dp[0][0] = 1;
        
        for(int i=1; i<=str.length(); i++){
            char ch = str.charAt(i-1);

            if(ch >= 'a' && ch <= 'z'){
                dp[0][i] = Math.min(dp[0][i-1] + 2, dp[1][i-1] + 2);
                dp[1][i] = Math.min(dp[0][i-1] + 2, dp[1][i-1] + 1);
            }
            else{
                dp[0][i] = Math.min(dp[0][i-1] + 1, dp[1][i-1] + 2);
                dp[1][i] = Math.min(dp[0][i-1] + 2, dp[1][i-1] + 2);
            }
        }

        //printList(dp, str.length());

        System.out.println(Math.min(dp[0][str.length()], dp[1][str.length()]));
    }
}