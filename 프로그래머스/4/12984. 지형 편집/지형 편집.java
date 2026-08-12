public class Solution {
    
    static int n;
    static int right, left;
    
    static int[][] land;
    static int P, Q;
    
    static long getCost(int floor){
        
        long cost = 0;
        
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(land[i][j] > floor){
                    cost += (long)(land[i][j] - floor) * Q;
                }else if(land[i][j] < floor){
                    cost += (long)(floor - land[i][j]) * P;
                }else{
                    continue;
                }
            }
        }
        
        return cost;
    }
    
    public long solution(int[][] land, int P, int Q) {
        
        this.land = land;
        this.P = P; this.Q = Q;
        
        n = land.length;
        
        right = Integer.MIN_VALUE;
        left = Integer.MAX_VALUE;
        
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                left = Math.min(left, land[i][j]);
                right = Math.max(right, land[i][j]);
            }
        }
        
        if(left == right){
            return 0;
        }
        
        long answer = Long.MAX_VALUE;
        
        while(left < right){
            int mid = (left + right) / 2;
            
            long cost1 = getCost(mid);
            long cost2 = getCost(mid + 1);
            
            if(cost1 > cost2){
                left = mid + 1;
                answer = Math.min(answer, cost2);
            }else{
                right = mid;
                answer = Math.min(answer, cost1);
            }
        }
        
        return answer;
    }
}