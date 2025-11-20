
import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		
		//SystemsetIn(new FileInputStream("res/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine().trim());
        
        for(int tc=1; tc<=T; tc++){
         	int tc_num = Integer.parseInt(br.readLine().trim());
            int[] arr = new int[101];
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            for(int i=0; i<1000; i++){
             	int score = Integer.parseInt(st.nextToken());
                arr[score]++;
            }
            
            int max = 0;
            int result = -1;
            for(int i=0; i<101; i++){
            	if(max <= arr[i]){
                    max = arr[i];
                    result = i;
                }
                else continue;
            }
            
            System.out.println("#"+tc_num+" "+result);
        }
	}
}