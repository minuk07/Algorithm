
import java.util.*;
import java.io.*;


class Solution
{
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        
        for(int tc = 1; tc <= T; tc++){
        	int n = Integer.parseInt(br.readLine().trim());
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            int[] arr = new int[n];
            
            for(int i=0; i<n; i++){
                int price = Integer.parseInt(st.nextToken());
                arr[i] = price;
            }
            
            long max = 0;
            long result = 0;
            for(int i=n-1; i>=0; i--){
             	   if(arr[i] < max){
                    	result += (max - arr[i]);   
                   }else{
                       max = arr[i];
                   }
            }
            
            System.out.println("#"+tc+" "+result);
        }
	}
}