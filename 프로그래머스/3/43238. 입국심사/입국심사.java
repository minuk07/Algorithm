import java.util.*;

class Solution {
    
    private boolean canProcess(long limit, int n, int[] times){
        long cnt = 0;
        
        for(int t : times){
            cnt += (limit / t);
            if(cnt >= n) return true;
        }
    
        return false;
    }
        
    public long solution(int n, int[] times) {
        long answer = 0;
        
        long left = 0;
        long right = Long.MAX_VALUE;
        
        while(left < right){
            long mid = left + (right - left) / 2;
            if(canProcess(mid, n, times)){
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        
        return left;
    }
}