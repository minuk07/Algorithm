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
import java.util.*;
import java.io.*;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            n = Integer.parseInt(br.readLine().trim());
            int[][] arr = new int[n][n];
            for(int i=0; i<n; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0; j<n; j++){
                    int num = Integer.parseInt(st.nextToken());
                    arr[i][j] = num;
                }
            }
            System.out.println("#" + tc);
            int[][] tmp1 = new int[n][n];
            make90(tmp1, arr);

            int[][] tmp2 = new int[n][n];
            make180(tmp2, arr);

            int[][] tmp3 = new int[n][n];
            make270(tmp3, arr);

            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    System.out.print(tmp1[i][j]);
                }
                System.out.print(" ");
                for(int j=0; j<n; j++){
                    System.out.print(tmp2[i][j]);
                }
                System.out.print(" ");
                for(int j=0; j<n; j++){
                    System.out.print(tmp3[i][j]);
                }
                System.out.println();
            }
        }
    }

    public static void make90(int[][] tmp, int[][] arr){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                tmp[j][n-i-1] = arr[i][j];
            }
        }
    }

    public static void make180(int[][] tmp, int[][] arr){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                tmp[n-i-1][n-j-1] = arr[i][j];
            }
        }
    }

    public static void make270(int[][] tmp, int[][] arr){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                tmp[n-j-1][i] = arr[i][j];
            }
        }
    }
}