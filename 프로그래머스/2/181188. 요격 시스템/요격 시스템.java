import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        
        Arrays.sort(targets, (a,b) -> a[1] - b[1]);
        
        int cnt = 0;
        int last = -1;
        
        for(int[] cur : targets){
            
            int start = cur[0];
            int end = cur[1];
            
            if(last < start){
                last = end - 1;
                cnt++;
            }
        }
        
        return cnt;
    }
}