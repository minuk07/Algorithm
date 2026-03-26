import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int n;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        long[][] arr = new long[n+1][2];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());

            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        arr[n][0] = arr[0][0];
        arr[n][1] = arr[0][1];

        double area = 0;
        
        for(int i=0; i<n; i++){
            area += (arr[i][0] * arr[i+1][1] - arr[i][1] * arr[i+1][0]);
        }

        System.out.printf("%.1f", Math.abs(area / 2));
    }
}