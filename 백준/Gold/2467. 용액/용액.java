import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int n;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = n-1;

        int sum = Integer.MAX_VALUE;
        int y = start;
        int x = end;
        
        while(start < end){
            int temp = arr[start] + arr[end];

            if(Math.abs(temp) <= sum){
                sum = Math.abs(temp);
                y = start;
                x = end;
            }

            if(temp < 0) start++;
            else if(temp > 0) end--;
            else break;
            
        }

        System.out.print(arr[y] + " " + arr[x]);
    }
}