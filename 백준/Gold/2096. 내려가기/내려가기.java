import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int n;
    static int[][] map;
    static Score[][] dp;

    static int maxResult;
    static int minResult;

    public static void printList(int[][] arr){
        for(int i=0; i<n+1; i++){
            for(int j=0; j<3; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printList(Score[][] arr){
        for(int i=0; i<n+1; i++){
            for(int j=0; j<3; j++){
                System.out.print(arr[i][j].min + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static class Score{
        int max, min;
        Score(int max, int min){
            this.max = max; this.min = min;
        }
    }

    public static boolean inRange(int y, int x){
        return (y>=0 && x>=0 && y<n+1 && x<3);
    }

    public static void solve(){
        for(int i=1; i<n+1; i++){
            for(int j=0; j<3; j++){
                int tMax = Integer.MIN_VALUE;
                int tMin = Integer.MAX_VALUE;
                if(inRange(i-1, j)){
                    tMax = Math.max(tMax, dp[i-1][j].max);
                    tMin = Math.min(tMin, dp[i-1][j].min);
                }
                if(inRange(i-1, j-1)){
                    tMax = Math.max(tMax, dp[i-1][j-1].max);
                    tMin = Math.min(tMin, dp[i-1][j-1].min);
                }
                if(inRange(i-1, j+1)){
                    tMax = Math.max(tMax, dp[i-1][j+1].max);
                    tMin = Math.min(tMin, dp[i-1][j+1].min);
                }
                dp[i][j].max = map[i][j] + tMax;
                dp[i][j].min = map[i][j] + tMin;

                if(i==n){
                    maxResult = Math.max(maxResult, dp[i][j].max);
                    minResult = Math.min(minResult, dp[i][j].min);
                }
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        map = new int[n+1][3];

        map[0][0] = 0; map[0][1] = 0; map[0][2] = 0;
        
        for(int i=1; i<n+1; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new Score[n+1][3];
        for(int i=0; i<3; i++){
            dp[0][i] = new Score(0, 0);
        }
        for(int i=1; i<=n; i++){
            for(int j=0; j<3; j++){
                dp[i][j] = new Score(Integer.MIN_VALUE, Integer.MAX_VALUE);
            }
        }

        maxResult = Integer.MIN_VALUE;
        minResult = Integer.MAX_VALUE;

        solve();

        System.out.print(maxResult+ " " + minResult);
    }
}