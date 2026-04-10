import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static final int DIV = 1000007;

    static int n,m,c;
    static int[][] map;
    static int[][][][] dp;

    static boolean inRange(int y, int x){
        return (y>=0 && x>=0 && y<n && x<m);
    }

    static int dfs(int y, int x, int prev, int remain){
        if(remain < 0) return 0;
        if(!inRange(y, x)) return 0;
        if(y == n-1 && x == m-1){
            if(remain == 0 && map[y][x] == 0){
                return 1;
            }
            if(remain == 1 && map[y][x] > prev){
                return 1;
            }
            return 0;
        }

        if(dp[y][x][prev][remain] != -1) return dp[y][x][prev][remain];

        if(map[y][x] > prev){
            dp[y][x][prev][remain] = (dfs(y+1, x, map[y][x], remain-1) + dfs(y, x+1, map[y][x], remain-1)) % DIV;
            return dp[y][x][prev][remain];
        }
        if(map[y][x] == 0){
            dp[y][x][prev][remain] = (dfs(y+1, x, prev, remain) + dfs(y, x+1, prev, remain)) % DIV;
            return dp[y][x][prev][remain];
        }

        return 0;
    }
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        dp = new int[n][m][c+1][c+1];

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                for(int k=0; k<=c; k++){
                    Arrays.fill(dp[i][j][k], -1);
                }
            }
        }

        for(int k=1; k<=c; k++){
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            map[y][x] = k;
         }

        for(int i=0; i<=c; i++){
            System.out.print(dfs(0,0,0,i) + " ");
        }
    }
}