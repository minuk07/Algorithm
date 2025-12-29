import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static final int INF = 100000;

    static int n;
    static int m;
    static int r;

    static int[] item;
    static int[][] map;

    public static void printList(int[][] arr){
        for(int i=1; i<n+1; i++){
            for(int j=1; j<n+1; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        item = new int[n+1];
        map = new int[n+1][n+1];

        for(int i=1; i<n+1; i++){
            for(int j=1; j<n+1; j++){
                if(i == j) map[i][j] = 0;
                else map[i][j] = INF;
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++){
            int t = Integer.parseInt(st.nextToken());
            item[i] = t;
        }

        for(int i=0; i<r; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map[a][b] = c;
            map[b][a] = c;
        }

        for(int k=1; k<n+1; k++){
            for(int i=1; i<n+1; i++){
                for(int j=1; j<n+1; j++){
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        int result = 0;

        for(int i=1; i<n+1; i++){
            int temp = 0;
            for(int j=1; j<n+1; j++){
                if(map[i][j] <= m) temp += item[j];
            }
            result = Math.max(temp, result);
        }

        //printList(map);        
        System.out.println(result);
    }
}