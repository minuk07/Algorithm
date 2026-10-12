class Solution {
    
    private boolean canGo(int friends, int[] stones, int k){
        
        int cnt = 0;
        
        for(int stone : stones){
            if(stone - friends <= 0){
                cnt++;
                if(cnt >= k) return false;
            }else{
                cnt = 0;
            }
        }
        
        return true;
    }
    
    public int solution(int[] stones, int k) {
        
        int left = 1;
        int right = 200_000_000;
        
        while(left < right){
            int mid = left + (right - left) / 2;
            
            if(canGo(mid, stones, k)){
                left = mid + 1;
            }else{
                right = mid;
            }
        }
        
        return left;
    }
}