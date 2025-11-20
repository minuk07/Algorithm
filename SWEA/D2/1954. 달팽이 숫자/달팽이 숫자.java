/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// double b = 1.0;               
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.util.Scanner;
import java.io.FileInputStream;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0 , -1, 0};
	public static void main(String args[]) throws Exception
	{
		
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int n = sc.nextInt();
            int[][] arr = new int[n][n];
            int y = 0;
            int x = 0;
            int dir = 0;
            int num = 1;
            arr[0][0] = 1;
            while(num < n*n){
                dir = dir % 4;
                num++;
            	y = y + dy[dir];
                x = x + dx[dir];

                arr[y][x] = num;
                if(dir == 0 && (x+1 >= n || arr[y][x+1] != 0)) dir++;
                else if (dir == 1 && (y+1 >= n || arr[y+1][x] != 0)) dir++;
                else if (dir == 2 && (x-1 < 0 || arr[y][x-1] != 0 )) dir++;
                else if (dir == 3 && (y-1 < 0 || arr[y-1][x] != 0 )) dir++;
            }
            System.out.println("#"+test_case);
            for(int i=0; i<n; i++){
             	for(int j=0; j<n; j++){
            		System.out.print(arr[i][j]+" ");
                }
                System.out.println();
            }
		}
	}
}