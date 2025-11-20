
import java.util.*;
import java.io.*;

class Solution
{
    static int[] dy = {0,0,1,-1};
    static int[] dx = {-1,1,0,0};
    static Queue<Node> q;
    static boolean[][] visited;
    static int[][] arr;
    static int n;
    static int m;
    
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
        
        for(int tc = 1; tc<=T; tc++){
         	StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            
            arr = new int[n][n];
            
           
            for(int i=0; i<n; i++){
                 st = new StringTokenizer(br.readLine());
            	for(int j =0; j<n; j++){
                	int num = Integer.parseInt(st.nextToken());
                    arr[i][j] = num;
                }
            }
            
            int max = Integer.MIN_VALUE;
            
            for(int i=0; i<=n-m; i++){
             	for(int j=0; j<=n-m; j++){
                 	   max = Integer.max(max, bfs(i,j));
                }
            }
            
            System.out.println("#" + tc + " " + max);
        }
	}
    
    	public static int bfs(int y, int x){
            	visited = new boolean[n][n];
            	q = new LinkedList<>();
            	int sum = 0;
            	sum += arr[y][x];
         	   visited[y][x] = true;
            	
            	q.add(new Node(y,x));
            
             while(!q.isEmpty()){
                 Node cur = q.poll();
                 int uy = cur.y;
                 int ux = cur.x;
              	   for(int i=0; i<4; i++){
                   		int ddy = uy + dy[i];
                       int ddx = ux + dx[i];
                       if(ddy<y || ddx < x || ddy >= y+m || ddx >= x+m || visited[ddy][ddx]) continue;
                       visited[ddy][ddx] = true;
                       q.add(new Node(ddy,ddx));
                       sum += arr[ddy][ddx];
                   }
             }
            return sum;
        }
        
        static class Node{
        	int  y;
            int x;
            Node(int y, int x){
             	this.y =y;
                this.x=x;
            }
            
        }
    
    
}