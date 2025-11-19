
import java.util.*;
import java.io.*;

class Solution
{
    static int[] cal = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        
	public static void main(String args[]) throws Exception
	{
		
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
        {
            int[] c = new int[4];
            int result = 0;
            for(int i=0; i<4; i++){
                int n = sc.nextInt();
                c[i] = n;
            }

            int startM = c[0];
            int startD = c[1];
            int endM = c[2];
            int endD = c[3];

            
                if(endM - startM > 0) {
                    for (int i = startM + 1; i < endM; i++) {
                        result += cal[i - 1];
                    }
                    result += endD;
           		    result += (cal[startM-1] - startD + 1);
                }
				else{
                 	result += (endD-startD+1);   
                }

            System.out.println("#"+test_case + " " + result);
        }
        
	}
}