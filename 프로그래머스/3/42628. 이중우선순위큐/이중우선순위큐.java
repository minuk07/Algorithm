import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        
        PriorityQueue<Integer> up = new PriorityQueue<>((a,b) -> a - b);
        PriorityQueue<Integer> down = new PriorityQueue<>((a,b) -> b - a);
        
        int n = operations.length;
        
        for(String s : operations){
            String[] str = s.split(" ");
            String cmd = str[0];
            int num = Integer.parseInt(str[1]);
            
            if(cmd.equals("I")){
                up.add(num);
                down.add(num);
            }
            else{
                if(up.isEmpty()) continue;
                
                if(num == 1){
                    int max = down.poll();
                    up.remove(max);
                }
                else {
                    int min = up.poll();
                    down.remove(min);
                }
            }
        }
        
        
        int[] answer = new int[2];
        
        if(up.isEmpty()) return answer;
        
        int max = down.peek();
        int min = up.peek();
    
        answer[0] = max;
        answer[1] = min;
        
        
        return answer;
    }
}