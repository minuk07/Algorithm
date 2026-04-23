class Solution {
    
    static boolean canGo(int[] stones, int k, int num){
        int cnt = 0;
        
        for(int stone : stones){
            if(stone - num < 0){
                cnt++;
                if(cnt >= k) return false;
            }
            else{
                cnt = 0;
            }
        }
        
        return true;
    }
    
    public int solution(int[] stones, int k) {
        int answer = 0;
        
        int max = 200000000;
        int min = 1;
        
        while(min <= max){
            int mid = (max + min) / 2;
            
            if(canGo(stones, k, mid)){
                answer = mid;
                min = mid + 1;
            }
            else{
                max = mid - 1;
            }
        }
        
        return answer;
    }
}