import java.util.*;

class Solution {
    
    int n;
    
    private boolean solve(int level, int[] diffs, int[] times, long limit){
        
        long time = times[0];
        
        for(int i=1; i<n; i++){
            int diff = diffs[i];
            int time_cur = times[i];
            int time_prev = times[i-1];
            
            if(diff <= level){
                time += time_cur;
            }else{
                time += (diff - level) * (time_cur + time_prev) + time_cur;
            }
        }
        
        if(time <= limit) return true;
        else return false;
    }
    
    public int solution(int[] diffs, int[] times, long limit) {
        
        n = diffs.length;
        
        int left = 1;
        int right = 100000;
        
        
        while(left < right){
            int mid = left + (right-left)/2;
            
            if(solve(mid, diffs, times, limit)){
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        
        return left;
    }
}