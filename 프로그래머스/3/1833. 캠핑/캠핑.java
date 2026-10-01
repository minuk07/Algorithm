import java.util.*;

class Solution {
    public int solution(int n, int[][] data) {
        int answer = 0;
        
        Arrays.sort(data, (a,b) -> {
            if(a[0] != b[0]) return a[0] - b[0];
            
            return a[1] - b[1];
        });
        
        for(int i=0; i<n; i++){
            
            int cx = data[i][0];
            int cy = data[i][1];
            
            int maxDown = Integer.MIN_VALUE;
            int maxUp = Integer.MAX_VALUE;
            
            int tmpDown = Integer.MIN_VALUE;
            int tmpUp = Integer.MAX_VALUE;
            
            int groupX = cx;
            
            for(int j=i; j<n; j++){
                int nx = data[j][0];
                int ny = data[j][1];
                
                if(cx == nx) continue;
                
                if(groupX != nx){
                    maxDown = Math.max(tmpDown, maxDown);
                    maxUp = Math.min(tmpUp, maxUp);
                    tmpDown = Integer.MIN_VALUE;
                    tmpUp = Integer.MAX_VALUE;
                    groupX = nx;
                }
                
                if(cy == ny) continue;
                
                if(cy > ny){ //down
                    
                    if(maxDown <= ny) answer++;
                    tmpDown = Math.max(tmpDown, ny);
                }
                if(cy < ny){ //up
                    if(maxUp >= ny) answer++;
                    tmpUp = Math.min(tmpUp, ny);
                }
            }
        }
        
        return answer;
    }
}