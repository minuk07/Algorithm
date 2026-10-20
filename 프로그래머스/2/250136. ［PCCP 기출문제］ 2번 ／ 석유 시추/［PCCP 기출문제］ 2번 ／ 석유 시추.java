import java.util.*;

class Solution {
    
    int[] dy = {0, 0, -1, 1};
    int[] dx = {1, -1, 0, 0};
    
    int n, m;
    boolean[][] visited;
    int[][] map;
    List<Integer> list;
    
    private boolean inRange(int y, int x){
        return (y>=0 && x>=0 && y<n && x<m);
    }
    
    private int bfs(int y, int x, int[][] land, int idx){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{y, x});
        visited[y][x] = true;
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
                visited[ny][nx] = true;
                map[ny][nx] = idx;
                cnt++;
                q.add(new int[]{ny, nx});
            }
        }
        
        return cnt;
    }
    
    public int solution(int[][] land) {
        int answer = 0;
        
        n = land.length;
        m = land[0].length;
        
        list = new ArrayList<>();
        
        int idx = 1;
        visited = new boolean[n][m];
        map = new int[n][m];
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(land[i][j] == 1 && !visited[i][j]){
                    list.add(bfs(i, j, land, idx++));
                }
            }
        }
        
        Set<Integer> set = new HashSet<>();
        
        for(int j=0; j<m; j++){
            set = new HashSet<>();
            for(int i=0; i<n; i++){
                if(map[i][j] != 0){
                    set.add(map[i][j]);
                }
            }
            
            int cnt = 0;
            for(int key : set){
                cnt += list.get(key - 1);
            }
            
            answer = Math.max(cnt, answer);
        }
        
        return answer;
    }
}