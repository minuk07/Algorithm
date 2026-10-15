import java.util.*;

class Solution {
    
    List<Puzzle> list;
    
    class Puzzle{
        int idx, diff, take;
        
        Puzzle(int idx, int diff, int take){
            this.idx=idx; this.diff=diff; this.take=take;
        }
    }
    
    private long canSolve(List<Puzzle> list, int level, long limit){
        
        long time = list.get(0).take;
        
        for(int i=1; i<list.size(); i++){
            int time_cur = list.get(i).take;
            int time_prev = list.get(i-1).take;
            int diff = list.get(i).diff;
            
            if(diff <= level){
                time += time_cur;
            }else{
                int cnt = diff - level;
                time += (cnt * (time_prev + time_cur));
                time += time_cur;
            }
        }
        
        return time;
    }
    
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        
        list = new ArrayList<>();
        
        int left = 1;
        int right = Integer.MIN_VALUE;
        
        for(int i=0; i<diffs.length; i++){
            list.add(new Puzzle(i, diffs[i], times[i]));
            right = Math.max(right, diffs[i]);
        }
        
        while(left < right){
            int mid = left + (right - left) / 2;
            if(canSolve(list, mid, limit) <= limit){                
                right = mid; 
            }else{
                left = mid + 1;
            }
        }
        
        
        return left;
    }
}