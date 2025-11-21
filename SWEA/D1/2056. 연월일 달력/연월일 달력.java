
import java.util.*;
import java.io.*;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{	
    static int[] cal = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30 , 31, 30, 31};
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        
        for(int tc=1; tc<=T; tc++){
         	String str = br.readLine();
            String yy = str.substring(0,4);
            String mm = str.substring(4,6);
            String dd = str.substring(6,8);
            
            System.out.print("#"+tc+" ");
            
            int month = Integer.parseInt(mm);
            //System.out.print(month);
            
            if(Integer.parseInt(dd) <= cal[month]){
             	System.out.println(yy+"/"+mm+"/"+dd);   
            }
            else{
             	System.out.println("-1");   
            }
        }
	}
}
