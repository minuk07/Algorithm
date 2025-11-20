
import java.util.*;
import java.io.*;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
    static int[] building = new int[1001];
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        

		for(int test_case = 1; test_case <= 10; test_case++)
		{
            
			int n = Integer.parseInt(br.readLine().trim());
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++){
            	int h = Integer.parseInt(st.nextToken());
                building[i] = h;
            }
            
            int result = 0;
            
            for(int i=2; i<n-2; i++){
             	int block = 0;
                for(int j=1; j<=2; j++){
                    
                 	if(building[i] <= building[i-j] ) block = building[i];
                    else{
                     	block = Math.max(building[i-j], block);   
                    }
                    
                    if(building[i] <= building[i+j] ) block = building[i];
                    else{
                     	block = Math.max(building[i+j],block);   
                    }
                }
                //if(i >=7) System.out.println((i+1)+"번째 block: "+block+" ");
                result += (building[i] - block);
            }
            
            System.out.println("#" + test_case + " " + result);
		}
	}
}