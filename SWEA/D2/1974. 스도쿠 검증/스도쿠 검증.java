
import java.util.*;
import java.io.*;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
    static int result;
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {1, -1, 0, 0};
    static int[][] arr;
    static boolean[] visited;
    static boolean[][] vis;
    static Queue<Node> q ;
    static int[] cnt;
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        
        for(int tc=1; tc<=T; tc++){ 
            arr = new int[9][9];
            
            for(int i=0; i<9; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0; j<9; j++){
                 	int num = Integer.parseInt(st.nextToken());
                    arr[i][j] = num;
                }
            }
           
            result = 1;
            
             for(int i=0; i<9; i+=3){
             	for(int j=0; j<9; j+=3){
                 	   vis = new boolean[9][9];
                       cnt = new int[10];
                       bfs(i,j);
                }
            }
            
            if(result != 0){
                for(int i=0; i<9; i++){
                    visited = new boolean[10];
                    horiDfs(i,0);
                }
            }
            if(result != 0){
                for(int i=0; i<9; i++){
                    visited = new boolean[10];
                    verDfs(0,i);
                }
            }
            System.out.println("#" + tc + " "+ result);
        }
	}

    
    static public void horiDfs(int y , int x){
        if(x >= 9 || x<0) return;
        
         if(visited[arr[y][x]]){
            // System.out.println("실패 y:"+y+" x:"+x+" ");
          	result = 0;
             return;
         }
        visited[arr[y][x]] = true;
        
        horiDfs(y, x+1);
    }
    
    static public void verDfs(int y, int x){
     	  if(y<0 || y>= 9) return;
         
        if(visited[arr[y][x]]){
         	result = 0;
            return;
        }
        visited[arr[y][x]] = true;
        
        verDfs(y+1, x);
    }
    
	public static void bfs(int y, int x){
     	   q = new LinkedList<>();
        	vis[y][x] = true;
        	cnt[arr[y][x]]++;
        	q.add(new Node(y,x));
        
            while(!q.isEmpty()){
                Node cur = q.poll();
                int uy = cur.y;
                int ux = cur.x;
        	
                for(int i=0; i<4; i++){
                    int ddy = uy + dy[i];
                    int ddx = ux + dx[i];

                    if(ddy < y || ddx < x|| ddy >= y+3 || ddx >= x+3 || vis[ddy][ddx]) continue; 
					
                    if(cnt[arr[ddy][ddx]] >= 1){
                        //System.out.println("실패 y:"+ddy+" x:"+ddx+" ");
                        result = 0;
                        return;
                    }	
                    
                    q.add(new Node(ddy,ddx));
                    vis[ddy][ddx] = true;
                    cnt[arr[ddy][ddx]]++;
                }
            }
    }
    
    public static class Node{
     	int y, x;
        Node(int y, int x){
         	this.y = y;
            this.x = x;
        }
    }
}