import java.util.*;

class Solution {
    
    int[] dy = {0, 0, -1, 1};
    int[] dx = {1, -1, 0, 0};
    
    int n, m;
    List<Integer> list;
    int[][] land;
    int[][] map;
    boolean[][] visited;
    
    private boolean inRange(int y, int x){
        return(y>=0 && x>=0 && y<n && x<m);
    }
    
    private int bfs(int y, int x, int idx){
        Queue<int[]> q = new LinkedList<>();
        visited[y][x] = true;
        q.add(new int[]{y, x});
        map[y][x] = idx;
        
        int cnt = 1;
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            
            for(int i=0; i<4; i++){
                int ny = cur[0] + dy[i];
                int nx = cur[1] + dx[i];
                
                if(!inRange(ny, nx)) continue;
                if(visited[ny][nx]) continue;
                if(land[ny][nx] == 0) continue;
                
                map[ny][nx] = idx;
                visited[ny][nx] = true;
                q.add(new int[]{ny, nx});
                cnt++;
            }
        }
        
        return cnt;
    }
    
    public int solution(int[][] land) {
        int answer = 0;
        
        this.land = land;
        n = land.length;
        m = land[0].length;
        
        map = new int[n][m];
        visited = new boolean[n][m];
        list = new ArrayList<>();
        int idx = 1;
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(land[i][j] == 1 && !visited[i][j]){
                    list.add(bfs(i, j, idx++));
                }
            }
        }
        
        Set<Integer> set = new HashSet<>();
        
        for(int j=0; j<m; j++){
            set = new HashSet<>();
            int sum = 0;
            for(int i=0; i<n; i++){
                if(map[i][j] != 0) set.add(map[i][j]);
            }
            
            for(int key : set){
                sum += list.get(key-1);
            }
            
            answer = Math.max(answer, sum);
        }
        
        return answer;
    }
}