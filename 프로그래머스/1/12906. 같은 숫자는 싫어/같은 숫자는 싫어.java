import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        
        Stack<Integer> q = new Stack<>();
        
        for(int i : arr){
            if(q.isEmpty()) q.push(i);
            else if(q.peek() == i) continue;
            else q.push(i);
        }
        
        int[] answer = new int[q.size()];
        
        for (int i = q.size() - 1; i >= 0; i--) {
            answer[i] = q.pop();
        }

        return answer;
    }
}