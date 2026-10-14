import java.util.*;

class Solution {
    
    int[] dy = {1, -1, 0, 0};
    int[] dx = {0, 0, -1, 1};
    
    private boolean inRange(int y, int x){
        return (y>=0 && x>=0 && y<5 && x<5);
    }
    
    private boolean bfs(int y, int x, String[] place){
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[5][5];
        
        visited[y][x] = true;
        q.add(new int[]{y, x, 0});
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            
            if(place[cur[0]].charAt(cur[1]) == 'P' && !(cur[0] == y && cur[1] == x)){
                //System.out.println("(" + y + "," + x + ")(" + cur[0] + "," + cur[1] + ")");
                if(cur[2] <= 2) return false;
            }
            
            for(int i=0; i<4; i++){
                int ny = cur[0] + dy[i];
                int nx = cur[1] + dx[i];
                
                if(!inRange(ny, nx)) continue;
                if(visited[ny][nx]) continue;
                if(place[ny].charAt(nx) == 'X') continue;
                
                visited[ny][nx] = true;
                q.add(new int[]{ny, nx, cur[2] + 1});
            }
        }
        
        return true;
    }
    
    private boolean isFollowRule(String[] place){
        
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                if(place[i].charAt(j) == 'P'){
                    if(!bfs(i, j, place)){
                        return false;
                    }else{
                        continue;
                    }
                }
            }
        }
        
        return true;
    }
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        
        for(int i=0; i<5; i++){
            if(isFollowRule(places[i])){
                answer[i] = 1;
            }else{
                answer[i] = 0;
            }
        }
        
        return answer;
    }
}