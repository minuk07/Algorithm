import java.util.*;

class Solution {
    
    static boolean isPossible(int n, int[] times, long time){
        
        long sum = 0;
        
        for(int t : times){
            sum += (time / t);
        }
        
        if(sum >= n) return true;
        else return false;
    }
    
    public long solution(int n, int[] times) {
        long answer = 0;
        
        Arrays.sort(times);
        
        long min = 0;
        long max = (long) n * times[times.length - 1];
        
        while(min <= max){
            long mid = (min + max) / 2;
            
            if(isPossible(n, times, mid)){
                answer = mid;
                max = mid - 1;
            }
            else{
                min = mid + 1;
            }
        }
        
        
        return answer;
    }
}