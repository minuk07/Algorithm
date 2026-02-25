import java.util.*;

class Solution {
    
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    
    static int[][] map;
    static boolean[][] visited;
    static List<Integer> list;
    static int n, m;
    
    static boolean inRange(int y, int x){
        return (y>=0 && x>=0 && y<n && x<m);
    }
    
    public int[] solution(String[] maps) {
        
        n = maps.length;
        m = maps[0].length();
        
        map = new int[n][m];
        visited = new boolean[n][m];
        list = new ArrayList<>();
        
        for(int i=0; i<n; i++){
            String str = maps[i];
            for(int j=0; j<m; j++){
                if(str.charAt(j) == 'X'){
                    map[i][j] = 0;
                }
                else{
                    map[i][j] = str.charAt(j) - '0';
                }
            }
        }
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(map[i][j] != 0 && !visited[i][j]){
                    list.add(bfs(i,j));
                }
            }
        }
        
        if(list.isEmpty()){
            return new int[]{-1};
        }
        
        Collections.sort(list);
        
        int[] result = new int[list.size()];
        
        for(int i=0; i<list.size(); i++){
            result[i] = list.get(i);
        }
        
        return result;
    }
    
    static int bfs(int y, int x){
        int cnt = map[y][x];
        Queue<int[]> q = new LinkedList<>();
        visited[y][x] = true;
        q.add(new int[]{y, x});
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            
            for(int i=0; i<4; i++){
                int ddy = cur[0] + dy[i];
                int ddx = cur[1] + dx[i];
                
                if(!inRange(ddy, ddx)) continue;
                if(map[ddy][ddx] == 0) continue;
                if(visited[ddy][ddx]) continue;
                
                cnt += map[ddy][ddx];
                visited[ddy][ddx] = true;
                q.add(new int[]{ddy, ddx});
            }
        }
        
        return cnt;
    }
}