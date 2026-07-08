import java.util.*;

class Solution {
    
    public long solution(int n, int m, int x, int y, int[][] queries) {
        long answer = -1;
        
        long left = y;
        long right = y;
        long top = x;
        long bottom = x;
        
        for(int i=queries.length-1; i>=0; i--){
            int command = queries[i][0];
            int dx = queries[i][1];
            
            if(command == 0){ 
                if(left != 0) left += dx;
                right = Math.min(m-1, right + dx);
            }else if(command == 1){ 
                if(right != m-1) right -= dx;
                left = Math.max(0, left - dx);
            }else if(command == 2){
                if(top != 0) top += dx;
                bottom = Math.min(n-1, bottom + dx);
            }else{
                if(bottom != n-1) bottom -= dx;
                top = Math.max(0, top - dx);
            }
            
            if(left >= m || right < 0 || top >= n || bottom < 0) return 0;
        }
        
        return (right - left + 1) * (bottom - top + 1);
    }
}