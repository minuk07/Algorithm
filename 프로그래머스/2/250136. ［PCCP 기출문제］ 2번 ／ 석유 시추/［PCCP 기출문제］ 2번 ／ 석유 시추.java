import java.util.*;

class Solution {
    
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    
    static int[][] land;
    static int n,m;
    
    static int size;
    static int[][] map;
    static boolean[][] visited;
    
    static List<Integer> list;
    
    static boolean inRange(int y, int x){
        return (y>=0 && x>=0 && y<n && x<m);
    }
    
    static void dfs(int y, int x, int idx){
        
        visited[y][x] = true;
        map[y][x] = idx;
        size++;
        
        for(int i=0; i<4; i++){
            int ny = y + dy[i];
            int nx = x + dx[i];
            
            if(!inRange(ny, nx)) continue;
            if(land[ny][nx] == 0) continue;
            if(visited[ny][nx]) continue;
            
            dfs(ny, nx, idx);
        }
        
        return;
    }
    
    public int solution(int[][] land) {
        int answer = 0;
        
        this.land = land;
        
        n = land.length;
        m = land[0].length;
        
        map = new int[n][m];
        visited = new boolean[n][m];
        list = new ArrayList<>();
        
        size = 0;
        
        int idx = 1;
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(!visited[i][j] && land[i][j] == 1){
                    size = 0;
                    dfs(i, j, idx);
                    list.add(size);
                    idx++;
                }
            }
        }
        
        // for(int i=0; i<n; i++){
        //     for(int j=0; j<m; j++){
        //         System.out.print(map[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        
        for(int row=0; row<m; row++){
            Set<Integer> set = new HashSet<>();
            int tmp = 0;
            
            for(int col=0; col<n; col++){
            
                if(map[col][row] != 0){
                    set.add(map[col][row]);
                }   
            }
            
            for(int i : set){
                tmp += list.get(i-1);
            }
            
            answer = Math.max(answer, tmp);
        }
        
        return answer;
    }
}