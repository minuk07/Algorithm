import java.util.*;

class Solution {
    
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    
    static int n, m;
    static int[][] map;
    
    static List<Integer> list;
    static boolean[][] visited;
    
    static boolean inRange(int y, int x){
        return (y>=0 && x>=0 && y<n && x<m);
    }
    
    static void bfs(int y, int x){
        
        Queue<int[]> q = new LinkedList<>();
        
        int cnt = map[y][x];
        
        q.add(new int[]{y, x});
        visited[y][x] = true;
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            
            int cy = cur[0]; int cx = cur[1];
            
            for(int i=0; i<4; i++){
                int ny = cy + dy[i];
                int nx = cx + dx[i];
                
                if(!inRange(ny, nx)) continue;
                if(map[ny][nx] == -1) continue;
                if(visited[ny][nx]) continue;
                
                visited[ny][nx] = true;
                q.add(new int[]{ny, nx});
                cnt += map[ny][nx];
            }
        }
        
        list.add(cnt);
    }
    
    public int[] solution(String[] maps) {
        
        n = maps.length;
        m = maps[0].length();
        
        list = new ArrayList<>();
        map = new int[n][m];
        visited = new boolean[n][m];
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                char tmp = maps[i].charAt(j);
                
                if(tmp == 'X'){
                    map[i][j] = -1;
                }else{
                    map[i][j] = tmp - '0';
                }
            }
        }
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(!visited[i][j] && map[i][j] > 0){
                    bfs(i, j);
                }
            }
        }
        
        if(list.isEmpty()){
            list.add(-1);
        }
        
        int[] answer = new int[list.size()];
        for(int i=0; i<list.size(); i++){
            answer[i] = list.get(i);
        }
        
        Arrays.sort(answer);
        
        return answer;
    }
}