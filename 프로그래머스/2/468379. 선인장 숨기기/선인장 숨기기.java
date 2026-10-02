import java.util.*;

class Solution {
    
    final int INF = Integer.MAX_VALUE;
    
    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        
        int[][] map = new int[m][n];
        
        for(int i=0; i<m; i++){
            Arrays.fill(map[i], INF);
        }
        
        int time = 1;
        
        for(int[] drop : drops){
            map[drop[0]][drop[1]] = time;
            time++;
        }
        
        int[][] minRow = new int[m][n-w+1]; //가로
        int[][] minCol = new int[m-h+1][n-w+1]; //세로
        
        Deque<Integer> q = new ArrayDeque<>();
        
        for(int i=0; i<m; i++){
            q = new ArrayDeque<>();
            
            for(int j=0; j<n; j++){
                int now = map[i][j];
                
                while(!q.isEmpty() && map[i][q.peekLast()] >= now){
                    q.pollLast();
                }
                
                q.addLast(j);
                
                if(q.peekFirst() <= j-w) q.pollFirst();
                if(j >= w-1) minRow[i][j-w+1] = map[i][q.peekFirst()];
            }
        }
        
        for(int j=0; j<n-w+1; j++){
            q = new ArrayDeque<>();
            
            for(int i=0; i<m; i++){
                int now = minRow[i][j];
                
                while(!q.isEmpty() && minRow[q.peekLast()][j] >= now){
                    q.pollLast();
                }
                
                q.addLast(i);
                
                if(q.peekFirst() <= i-h) q.pollFirst();
                if(i >= h-1) minCol[i-h+1][j] = minRow[q.peekFirst()][j];
            }
        }
        
        int bestY = 0, bestX = 0;
        int bestTime = 0;
        
        for(int i=0; i<minCol.length; i++){
            for(int j=0; j<minCol[i].length; j++){
                if(bestTime < minCol[i][j]){
                    bestTime = minCol[i][j];
                    bestY = i;
                    bestX = j;
                }
            }
        }
        
        return new int[]{bestY, bestX};
    }
}