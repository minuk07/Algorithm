import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        
        int sum = sequence[0];
        int start = 0;
        int end = 0;
        int min = Integer.MAX_VALUE;
        
        while(start <= sequence.length){
            if(sum == k ){
                //System.out.println(start + " " + end);
                if(min > (end - start)){
                    answer[0] = start;
                    answer[1] = end;
                    min = end - start;
                }
            }
            if(sum < k ){
                end++;
                if(end >= sequence.length) break;
                sum += sequence[end];
            }
            else{
                sum -= sequence[start];
                start++;
            }
            
        }
        
        return answer;
    }
}