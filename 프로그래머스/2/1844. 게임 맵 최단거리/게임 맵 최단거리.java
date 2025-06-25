import java.util.*;

class Solution {
    int dy[] = {-1, 1, 0, 0};
    int dx[] = {0, 0, 1, -1};
    int[][] visited;
    int result = 0;
    int n, m;
    
    public int solution(int[][] maps) {
        
        n = maps.length;
        m = maps[0].length;
        
        visited = new int[n][m];
        
        bfs(maps, visited, 0, 0, n, m);
        
        // for(int i=0; i<n; i++){
        //     for(int j=0; j<m; j++){
        //         System.out.print(maps[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        
        if(visited[n-1][m-1] == 0){
            return -1;
        }
        
        return maps[n-1][m-1] + 1;
    }
    
    public void bfs(int[][] maps,int[][] visited, int y, int x, int rows, int cols){
        
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {y,x});
        maps[y][x] = 0;
        
        while(!q.isEmpty()){
            int cur[] = q.remove();
            int uy = cur[0];
            int ux = cur[1];
            
            for(int i=0; i<4; i++){
                int ddy = uy + dy[i];
                int ddx = ux + dx[i];
                
                if(ddy>=0 && ddx >= 0 && ddy<rows && ddx<cols && maps[ddy][ddx] == 1 && visited[ddy][ddx] == 0){
                    q.add(new int[] {ddy, ddx});
                    maps[ddy][ddx] = maps[uy][ux] + 1;
                    visited[ddy][ddx] = 1;
                }
            }
        }
    }
}