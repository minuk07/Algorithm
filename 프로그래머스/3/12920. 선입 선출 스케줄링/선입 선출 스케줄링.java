import java.util.*;

class Solution {
    
    static int getTime(int time, int[] cores){
        int total = 0;
        
        for(int take : cores){
            total += (time / take) + 1;
        }
        
        return total;
    }
    
    static int process(int time, int[] cores){
        int total = 0;
        
        for(int take : cores){
            total += (time / take);
        }
        
        return total;
    }
    
    public int solution(int n, int[] cores) {
        int answer = 0;
        
        int left = 1;
        int right = 100_000_000;
        
        while(left < right){
            
            int mid = (left + right) / 2;
            
            if(getTime(mid, cores) < n){
                left = mid + 1;
            }else{
                right = mid;
            }
            
        }
        
        int cnt = cores.length;
        cnt += process(left - 1, cores);
        
        for(int i=0; i<cores.length; i++){
            if(left % cores[i] == 0){
                cnt++;
                if(cnt == n){
                    answer = i + 1;
                }
            }
        }
        
        return answer;
    }
}