class Solution {
    
    static boolean canTake(long time, int n, int[] times){
        long cnt = 0;
        
        for(int t : times){
            cnt += (time / t);
        }
        
        if(cnt >= n){
            return true;
        }else{
            return false;
        }
    }
    
    public long solution(int n, int[] times) {
    
        long left = 0;
        long right = 1_000_000_000L * 1_000_000_000L;
        
        while(left < right){
            long mid = left + (right - left) / 2;
            //System.out.println(mid);
            
            if(canTake(mid, n, times)){
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        
        return left;
    }
}