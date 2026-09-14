import java.util.*;

class Solution {
    public int[] solution(long begin, long end) {
        int size = (int)(end - begin + 1);
        int[] answer = new int[size];
        
        for (long i=begin;i<=end;i++){
            int idx = (int) (i-begin);
            answer[idx] = number((int)i);
        }
        
        return answer;
    }
    
    public int number(int num){
        if (num <= 1) return 0;
        double limit = Math.sqrt(num);
        int answer = 1;
        
        for (int i=2; i<= limit ; i++){
            int other = num / i;
            if (num % i == 0){
                if (other <= 10_000_000) return other;
                if (i <= 10_000_000) answer = Math.max(answer, i);
                else break;
            }
        }
        return answer;
    }
}