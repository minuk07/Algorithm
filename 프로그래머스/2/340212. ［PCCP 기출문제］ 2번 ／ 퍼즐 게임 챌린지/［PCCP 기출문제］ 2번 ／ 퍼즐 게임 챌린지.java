class Solution {
    
    static boolean isPossible(long level, int[] diffs, int[] times, long limit){
        
        long t = times[0];
        
        for(int i=1; i<times.length; i++){
            int diff = diffs[i];
            
            if(diff <= level){
                t += times[i];
            }
            else{
                int time_prev = times[i-1];
                int time_cur = times[i];
                
                t += ((diff - level) * (time_prev + time_cur) + time_cur);
            }
        }
        
        return (t <= limit);
    }
    
    
    public int solution(int[] diffs, int[] times, long limit) {
        long left = 1; 
        long right = limit;
        
        while(left < right){
            
            long mid = (left + right) / 2;
            
            if(isPossible(mid, diffs, times, limit)){
                right = mid;
            }
            else{
                left = mid + 1;
            }
        }
        
        return (int)left;
    }
}