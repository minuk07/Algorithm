import java.util.*;

class Solution {
    public int[] solution(int n, int[] numlist) {
        
        List<Integer> list = new ArrayList<>();
        
        for(int num : numlist){
            if(num % n == 0){
                list.add(num);
            }
        }
        
        int len = list.size();
        
        int[] answer = new int[len];
        for(int i=0; i<len; i++){
            answer[i] = list.get(i);
        }
    
        return answer;
    }
}