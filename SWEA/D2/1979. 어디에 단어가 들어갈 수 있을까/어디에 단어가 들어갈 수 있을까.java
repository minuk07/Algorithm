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
    static int[][] map;
    static boolean[][] visited;
    static int n;
    static int k;
    static int tmp;
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        
        
        for(int tc=1; tc<=T; tc++){
         	StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            
            map = new int[n][n];
            
            for(int i=0; i<n; i++){
                st = new StringTokenizer(br.readLine());
             	for(int j=0; j<n; j++){
                 	int num = Integer.parseInt(st.nextToken());
                    map[i][j] = num;
                }
            }
            int result = 0;
            
            visited = new boolean[n][n];
            for(int i=0; i<n; i++){
             	for(int j=0; j<n; j++){
                 	if(!visited[i][j] && map[i][j] == 1){
                        tmp=0;
                     	horDfs(i,j);
                        if(tmp == k) result++;
                    }
                }
            }
            
            visited = new boolean[n][n];
            for(int i=0; i<n; i++){
             	for(int j=0; j<n; j++){
                 	if(!visited[i][j] && map[i][j] == 1){
                        tmp=0;
                     	verDfs(i,j);
                        if(tmp == k) result++;
                    }
                }
            }
            
            System.out.println("#"+tc + " " +result);
        }
	}
    
    public static void horDfs(int y, int x){
        if(y<0 || x<0 || y>=n || x>=n || map[y][x] == 0 || visited[y][x]) return;
        visited[y][x] = true;
        tmp++;
        horDfs(y, x+1);
    }
    
    public static void verDfs(int y, int x){
     	 if(y<0 || x<0 || y>=n || x>=n || map[y][x] == 0 || visited[y][x]) return;
        visited[y][x] = true;
        tmp++;
        verDfs(y+1, x);   
    }
}