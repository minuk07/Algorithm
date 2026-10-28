import java.util.*;

class Solution {
    
    List<List<int[]>> totalPath;
    
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        
        int n = points.length;
        int x = routes.length;
        
        int r = 0;
        int c = 0;
        for(int[] p : points){
            r = Math.max(r, p[0]);
            c = Math.max(c, p[1]);
        }
        
        totalPath = new ArrayList<>();
        
        for(int i=0; i<x; i++){
            int m = routes[i].length;
            List<int[]> path = new ArrayList<>();
            
            int curR = points[routes[i][0] - 1][0];
            int curC = points[routes[i][0] - 1][1];
            
            path.add(new int[]{curR, curC});
            
            for(int j=1; j<m; j++){
                int destR = points[routes[i][j] - 1][0];
                int destC = points[routes[i][j] - 1][1];
                
                while(curR != destR){
                    if(curR > destR) curR--;
                    else curR++;
                    
                    path.add(new int[]{curR, curC});
                }
                
                while(curC != destC){
                    if(curC > destC) curC--;
                    else curC++;
                    
                    path.add(new int[]{curR, curC});
                }
            }
            totalPath.add(path);
        }
        
        int time = 0;
        
        for(List<int[]> list : totalPath){
            time = Math.max(time, list.size());
        }
                
        for(int t=0; t<time; t++){
            int[][] map = new int[r+1][c+1];
            
            for(List<int[]> path : totalPath){
                if(t >= path.size()) continue;
                int[] cur = path.get(t);
                map[cur[0]][cur[1]]++;
            }
            
            for(int i=0; i<=r; i++){
                for(int j=0; j<=c; j++){
                    if(map[i][j] > 1) answer++;
                }
            }
        }
        
        return answer;
    }
}