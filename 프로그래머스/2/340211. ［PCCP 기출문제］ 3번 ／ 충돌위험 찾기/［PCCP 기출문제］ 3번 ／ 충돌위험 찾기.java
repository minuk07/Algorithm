import java.util.*;

class Solution {
    
    static int r,c;
    static int n, m, x;
    
    static List<int[]> pointList;
    static List<List<int[]>> totalPath;

    
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        
        n = points.length;
        m = routes.length;
        x = routes[0].length;
        
        pointList = new ArrayList<>();
        pointList.add(new int[]{0, 0});
        
        r = 0; 
        c = 0;
        
        for(int i=0; i<points.length; i++){
            pointList.add(new int[]{points[i][0] - 1, points[i][1] - 1});
            r = Math.max(r, points[i][0]);
            c = Math.max(c, points[i][1]);
        }
        
        int maxTime = 0;
        totalPath = new ArrayList<>();
        
        for(int i=0; i<m; i++){
            
            List<int[]> path = new ArrayList<>();
            
            int[] start = pointList.get(routes[i][0]);
            int curR = start[0];
            int curC = start[1];
            
            path.add(new int[]{curR, curC});
            
            for(int j=1; j<x; j++){
                int[] dest = pointList.get(routes[i][j]);
                int destR = dest[0];
                int destC = dest[1];
                
                while(curR != destR){
                    if(curR < destR) curR++;
                    else curR--;
                    
                    path.add(new int[]{curR, curC});
                }
                
                while(curC != destC){
                    if(curC < destC) curC++;
                    else curC--;
                    
                    path.add(new int[]{curR, curC});
                }
            }
            
            totalPath.add(path);
            maxTime = Math.max(maxTime, path.size() - 1);
        }
        
        for(int time=0; time<=maxTime; time++){
            int[][] map = new int[r+1][c+1];
            
            for(int robot=0; robot<m; robot++){
                List<int[]> path = totalPath.get(robot);
                if(time < path.size()){
                    int[] move = path.get(time);
                    map[move[0]][move[1]]++;
                }
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