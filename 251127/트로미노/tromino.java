import java.util.*;


public class Main {

    static int[][] grid;
    static int n;
    static int m;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = sc.nextInt();
            }
        }


        int result = 0;

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                result = Math.max(result, block1(i,j));
                result = Math.max(result, block2(i,j));
                result = Math.max(result, block3(i,j));
            }
        }

        System.out.println(result);
    }

    public static int block1(int y, int x){
        int sum = 0;
        int max = 0;

        if(x-1 >= 0 && y-1 >=0){
            sum += grid[y][x-1];
            sum += grid[y-1][x];
            max = Math.max(sum, max);
        }

        sum =0;

        if(x+1 < m && y-1 >=0){
            sum += grid[y][x+1];
            sum += grid[y-1][x];
            max = Math.max(sum, max);
        }

        sum = 0;

        if(x-1 >= 0 && y+1 < n){
            sum += grid[y][x-1];
            sum += grid[y+1][x];
            max = Math.max(sum, max);
        }

        sum = 0;

        if(x+1 < m && y+1 < n){
            sum += grid[y][x+1];
            sum += grid[y+1][x];
            max = Math.max(sum, max);
        }

        return max + grid[y][x];
        
    }

    public static int block2(int y, int x){
        int sum = 0;
       

        for(int i=y; i<y+3; i++){
            if(i < 0 || i >= n) return 0;
            sum += grid[i][x];
        }

        return sum;
    }

    public static int block3(int y, int x){
        int sum = 0;

        for(int i=x; i<x+3; i++){
            if(i<0 || i>=m) return 0;
            sum += grid[y][i];
        }

        return sum;
    }
}