import java.util.*;

class Solution {
    
    int[] dy = {1,-1,0,0};
    int[] dx = {0,0,1,-1};
    
    int n, m;
    boolean[][] processed;
    boolean[][] canAccess;
    String[] storage;
    
    List<int[]> list;
    
    private boolean inRange(int y, int x){
        return(y>=0 && x>=0 && y<n && x<m);
    }
    
    private boolean isEdge(int y, int x){
        return(y==0 || x== 0 || y==n-1 || x==m-1);
    }
    
    private void bfs(int y, int x, char container){
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        
        q.add(new int[]{y, x});
        visited[y][x] = true;
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            
            for(int i=0; i<4; i++){
                int ny = cur[0] + dy[i];
                int nx = cur[1] + dx[i];
                
                if(!inRange(ny, nx)) continue;
                if(visited[ny][nx]) continue;
                if(processed[ny][nx]){
                    q.add(new int[]{ny, nx});
                    visited[ny][nx] = true;
                    continue;
                }
                if(storage[ny].charAt(nx) == container){
                    list.add(new int[]{ny, nx});
                    visited[ny][nx] = true;
                }
            }
        }
    }
    
    private void process(List<int[]> list){
        for(int[] l : list){
            processed[l[0]][l[1]] = true;
        }
    }
    
    private void crane(char container){
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(!processed[i][j] && storage[i].charAt(j) == container){
                    processed[i][j] = true;
                }
            }
        }
    }
    
    private void process(char container, int len){
        if(len == 1){
            list = new ArrayList<>();
            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    char cur = storage[i].charAt(j);
                    if(isEdge(i,j) && cur == container && !processed[i][j]){
                        list.add(new int[]{i, j});
                    }else if(isEdge(i,j) && processed[i][j]){
                        bfs(i, j, container);
                    }
                }
            }
            
            process(list);
        }
        else{
            crane(container);
        }
    }
    
    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        this.storage = storage;
        n = storage.length;
        m = storage[0].length();
        
        processed = new boolean[n][m];
        
        for(String req : requests){
            char container = req.charAt(0);
            int len = req.length();
            
            process(container, len);
        }
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(!processed[i][j]) answer++;
            }
        }
        
        return answer;
    }
}